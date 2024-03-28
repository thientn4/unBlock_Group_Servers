package com.unBlock_Group_Servers.model.object;

import java.sql.Timestamp;

public class Post {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Post(int id, String groupId, String opEmail, int commentTo, int replyTo, String title, String content, Boolean isPrivate, Boolean highlight, Timestamp datetime) {
        this.id = id;
        this.groupId = groupId;
        this.opEmail = opEmail;
        this.commentTo = commentTo;
        this.replyTo = replyTo;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.highlight = highlight;
        this.datetime = datetime;
    }

    private int id;
    private String groupId;
    private String opEmail;
    private int commentTo;
    private int replyTo;
    private String title;
    private String content;
    private Boolean isPrivate;
    private Boolean highlight;
    private Timestamp datetime;

}