package com.bdj.blooddonateproject.post_find_donator.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post_find_donator", schema = "v1")
public class PostFindDonator {
    @Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(name = "post_generator", sequenceName = "post_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private Long content;

    private Integer createAt;

    private Integer updateAt;

    private Integer deadlineRegister;

    private Boolean isDeleted;

    public Boolean isIsDeleted() {
        return this.isDeleted;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @OneToMany(mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<RegisterToDonate> register = new ArrayList<>();

    public List<RegisterToDonate> getRegister() {
        return this.register;
    }

    public void setRegister(List<RegisterToDonate> register) {
        this.register = register;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContent() {
        return this.content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public Integer getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public Integer getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getDeadlineRegister() {
        return this.deadlineRegister;
    }

    public void setDeadlineRegister(Integer deadlineRegister) {
        this.deadlineRegister = deadlineRegister;
    }

    public Hospital getHospital() {
        return this.hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public PostFindDonator() {
    }

    public PostFindDonator(Long content, Integer createAt, Integer updateAt, Integer deadlineRegister,
            Boolean isDeleted) {
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deadlineRegister = deadlineRegister;
        this.isDeleted = isDeleted;
    }

}
