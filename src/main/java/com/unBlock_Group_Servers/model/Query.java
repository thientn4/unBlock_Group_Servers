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

    public List<String> getPostTags(String postId){
        List<String>results=this.sql.query(
                "CALL UNBLOCK.GET_POST_TAGS(:postId)",
                new MapSqlParameterSource().addValue("postId", postId, Types.INTEGER),
                (row,rowNum)-> row.getString("TAG")
        );
        return results;
    }
    public List<Post> getReplies(String postId){
        List<Post>results=this.sql.query(
                "CALL UNBLOCK.GET_REPLIES(:postId)",
                new MapSqlParameterSource().addValue("postId", postId, Types.INTEGER),
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
                        row.getTimestamp("TIME_STAMP")
                )
        );
        return results;
    }
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
                        row.getTimestamp("TIME_STAMP")
                )
        );
        return results;
    }

    public int addPost(
            String groupId,
            String opEmail,
            int commentTo,
            int replyTo,
            String title,
            String content,
            boolean isPrivate,
            boolean highlight
    ){
        List<Integer>results=this.sql.query(
                "CALL UNBLOCK.ADD_POST(:groupId,:opEmail,:commentTo,:replyTo,:title,:content,:isPrivate,:highlight)",
                new MapSqlParameterSource()
                        .addValue("groupId", groupId, Types.VARCHAR)
                        .addValue("opEmail", opEmail, Types.VARCHAR)
                        .addValue("commentTo", commentTo!=-1?commentTo:null, Types.INTEGER)
                        .addValue("replyTo", replyTo!=-1?replyTo:null, Types.INTEGER)
                        .addValue("title", title, Types.VARCHAR)
                        .addValue("content", content, Types.VARCHAR)
                        .addValue("isPrivate", isPrivate, Types.BOOLEAN)
                        .addValue("highlight", highlight, Types.BOOLEAN),
                (row,rowNum)-> row.getInt("ID")
        );
        return results.get(0);
    }


    public int addPostTag(
            int postId,
            String tag
    ){
        return sql.update(
                "CALL UNBLOCK.ADD_POST_TAG(:tag,:postId)",
                new MapSqlParameterSource()
                        .addValue("tag", tag, Types.VARCHAR)
                        .addValue("postId", postId, Types.INTEGER)
        );
    }
}
