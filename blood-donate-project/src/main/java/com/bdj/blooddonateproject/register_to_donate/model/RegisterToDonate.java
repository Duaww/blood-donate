package com.bdj.blooddonateproject.register_to_donate.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;

@Entity
@Table(name = "register_to_donate", schema = "v1")
public class RegisterToDonate {

    @Id
    @GeneratedValue(generator = "register_generator")
    @SequenceGenerator(name = "register_generator", sequenceName = "register_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostFindDonator post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donator_id", nullable = false)
    private Donator donator;

    private Integer timeRegister;

    private Boolean isDonated;

    public Boolean getIsDonated() {
        return this.isDonated;
    }

    public void setIsDonated(Boolean isDonated) {
        this.isDonated = isDonated;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostFindDonator getPost() {
        return this.post;
    }

    public void setPost(PostFindDonator post) {
        this.post = post;
    }

    public Donator getDonator() {
        return this.donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

    public Integer getTimeRegister() {
        return this.timeRegister;
    }

    public void setTimeRegister(Integer timeRegister) {
        this.timeRegister = timeRegister;
    }

    public RegisterToDonate() {
    }

    public RegisterToDonate(Integer timeRegister, Boolean isDonated) {
        this.timeRegister = timeRegister;
        this.isDonated = isDonated;
    }

}
