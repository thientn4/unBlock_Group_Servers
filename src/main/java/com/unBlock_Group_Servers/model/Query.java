package com.unBlock_Group_Servers.model;

import com.unBlock_Group_Servers.model.object.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;

@Component //--------------------> mark a class as a BEAN
public class Query {
    @Autowired //--------------------> automates dependency (bean) injection
    private NamedParameterJdbcTemplate sql;

    public List<String> getGroupTags(String groupId){
        List<String>results=this.sql.query(
                "CALL UNBLOCK.GET_GROUP_TAGS(:groupId)",
                new MapSqlParameterSource().addValue("groupId", groupId, Types.VARCHAR),
                (row,rowNum)-> row.getString("TAG")
        );
        return results;
    }
    public List<Post> getGroupPosts(String groupId){
        List<Post>results=this.sql.query(
                "CALL UNBLOCK.GET_GROUP_POSTS(:groupId)",
                new MapSqlParameterSource().addValue("groupId", groupId, Types.VARCHAR),
                (row,rowNum)-> new Post(
                        row.getInt("ID"),
                        row.getString("GROUP_ID"),
                        row.getString("OP_EMAIL"),
                        row.getInt("COMMENT_TO"),
                        row.getInt("REPLY_TO"),
                        row.getString("TITLE"),
                        row.getString("CONTENT"),
                        row.getBoolean("IS_PRIVATE"),
                        row.getBoolean("HIGHLIGHT"),
                        row.getTimestamp("DATETIME")
                )
        );
        return results;
    }
}
