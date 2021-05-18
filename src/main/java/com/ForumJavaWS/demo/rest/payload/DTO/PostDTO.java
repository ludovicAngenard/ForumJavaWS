package com.ForumJavaWS.demo.rest.payload.DTO;

import java.util.Date;

import com.ForumJavaWS.demo.rest.entity.Topic;

public class PostDTO {

    private Long id;
    private String content;
    private Date updatedAt;
    private Topic topic;

    public PostDTO(String content, Date updatedAt, Topic topic){
        this.content = content;
        this.updatedAt = updatedAt;
        this.topic = topic;
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
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
