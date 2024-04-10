package com.unBlock_Group_Servers.model.object;

import java.sql.Array;
import java.sql.Timestamp;

public class Post {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getOpEmail() {
        return opEmail;
    }

    public void setOpEmail(String opEmail) {
        this.opEmail = opEmail;
    }

    public int getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(int commentTo) {
        this.commentTo = commentTo;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(int replyTo) {
        this.replyTo = replyTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getEditTimestamp() {
        return editTimestamp;
    }

    public void setEditTimestamp(Timestamp timestamp) {
        this.editTimestamp = timestamp;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Post(int id, int groupId, String opEmail, int commentTo, int replyTo, String title, String content, Boolean isPrivate, Boolean highlight, Timestamp datetime, Timestamp editDatetime, String tags) {
        this.id = id;
        this.groupId = groupId;
        this.opEmail = opEmail;
        this.commentTo = commentTo;
        this.replyTo = replyTo;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.highlight = highlight;
        this.timestamp = datetime;
        this.editTimestamp = editDatetime;
        this.tags = tags;
    }

    private int id;
    private int groupId;
    private String opEmail;
    private int commentTo;
    private int replyTo;
    private String title;
    private String content;
    private Boolean isPrivate;
    private Boolean highlight;
    private Timestamp timestamp;
    private Timestamp editTimestamp;

    private String tags;

}
