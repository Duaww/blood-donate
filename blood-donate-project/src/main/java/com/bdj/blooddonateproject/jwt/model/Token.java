package com.bdj.blooddonateproject.jwt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_token", schema = "v1")
public class Token {

    @Id
    @GeneratedValue(generator = "token_generator")
    @SequenceGenerator(name = "token_generator", sequenceName = "token_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 1000)
    private String token;

    private Date tokenExpDate;

    private Long creatorId;

    public Long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpDate() {
        return this.tokenExpDate;
    }

    public void setTokenExpDate(Date tokenExpDate) {
        this.tokenExpDate = tokenExpDate;
    }

    public Token() {
    }

    public Token(String token, Date tokenExpDate, Long creatorId) {
        this.token = token;
        this.tokenExpDate = tokenExpDate;
        this.creatorId = creatorId;
    }

}
