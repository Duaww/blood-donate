package com.bdj.blooddonateproject.post_find_donator.dto;

public class CreatePostDTO {

    private String content;

    private Integer deadlineRegister;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDeadlineRegister() {
        return this.deadlineRegister;
    }

    public void setDeadlineRegister(Integer deadlineRegister) {
        this.deadlineRegister = deadlineRegister;
    }

    public CreatePostDTO() {
    }

    public CreatePostDTO(String content, Integer deadlineRegister) {
        this.content = content;
        this.deadlineRegister = deadlineRegister;
    }

}
