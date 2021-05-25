package com.ForumJavaWS.demo.rest.payload.response;

public class MessageResponse {
    private Integer apiCode;

    private String message;

    public MessageResponse(Integer apiCode, String message) {
      this.apiCode = apiCode;
      this.message = message;
    }

    public Integer getApiCode() {
      return apiCode;
    }

    public String getMessage() {
      return message;
    }

    public void setApiCode(Integer apiCode) {
      this.apiCode = apiCode;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }
