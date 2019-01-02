package com.selfcabinet.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    private String userMessage;
    private String message;

    public Status(String message, String userMessage) {
        this.userMessage = userMessage;
        this.message = message;
    }

    public Status() {
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}