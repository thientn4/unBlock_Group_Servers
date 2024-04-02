package com.unBlock_Group_Servers.model.request;

import java.util.List;

public class AddPostRequest {
    public AddPostRequest(int groupId, String opEmail, int commentTo, int replyTo, String title, String content, Boolean isPrivate, Boolean isHighlight, List<String> tags) {
        this.groupId = groupId;
        this.opEmail = opEmail;
        this.commentTo = commentTo;
        this.replyTo = replyTo;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.isHighlight = isHighlight;
        this.tags = tags;
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
        return isHighlight;
    }

    public void setHighlight(Boolean highlight) {
        isHighlight = highlight;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private int groupId;
    private String opEmail;
    private int commentTo;
    private int replyTo;
    private String title;
    private String content;
    private Boolean isPrivate;
    private Boolean isHighlight;
    private List<String> tags;
}
