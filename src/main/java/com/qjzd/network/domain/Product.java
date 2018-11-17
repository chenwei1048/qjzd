package com.qjzd.network.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.qjzd.network.util.HtmlContextUtil;

import java.util.Date;

public class Product {
    private Long id;

    private Long type;

    private String typeName;

    private String title;

    private String picture;

    private String context;

    private String contextNoHtml;

    private Date createtime;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContextNoHtml() {
        return contextNoHtml;
    }

    public void setContextNoHtml(String contextNoHtml) {
        this.contextNoHtml = contextNoHtml;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
        if(this.context!=null){
            this.contextNoHtml = HtmlContextUtil.delHtmlTag(this.context);
        }
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}