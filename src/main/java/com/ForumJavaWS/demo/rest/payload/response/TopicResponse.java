package com.ForumJavaWS.demo.rest.payload.response;

public class TopicResponse {
    private Long id;
    private Boolean locked;

    public TopicResponse(){}

    public TopicResponse(Long id, Boolean locked){
        this.id = id;
        this.locked = locked;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public void setLocked(Boolean locked){
        this.locked = locked;
    }
    public Boolean getLocked(){
        return locked;
    }

}