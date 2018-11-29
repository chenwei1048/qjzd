package com.qjzd.network.domain;

import java.util.Date;

public class Newsinformation {
    private Long id;

    private Long type;

    private String title;

    private String picture;

    private String content;

    private String contentNoHtml;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContentNoHtml() {
        return contentNoHtml;
    }

    public void setContentNoHtml(String contentNoHtml) {
        this.contentNoHtml = contentNoHtml == null ? null : contentNoHtml.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}