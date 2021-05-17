package com.ForumJavaWS.demo.rest.payload.DTO;

import java.util.Date;

public class PostDTO {

    private Long id;
    private String content;
    private Date updatedAt;

    public PostDTO(String content, Date updatedAt){
        this.content = content;
        this.updatedAt = updatedAt;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
