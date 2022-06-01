package com.bdj.blooddonateproject.firebase.model;

import lombok.Data;

import java.util.Map;

@Data
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Note() {
    }

    public Note(String subject, String content, Map<String, String> data) {
        this.subject = subject;
        this.content = content;
        this.data = data;
    }

}
