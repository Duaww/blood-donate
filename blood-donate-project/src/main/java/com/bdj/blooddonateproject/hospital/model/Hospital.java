package com.bdj.blooddonateproject.hospital.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;
import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;
import com.bdj.blooddonateproject.user.model.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "hospital", schema = "v1")
public class Hospital {
    @Id
    @GeneratedValue(generator = "hospital_generator")
    @SequenceGenerator(name = "hospital_generator", sequenceName = "hospital_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String name;

    private String address;

    private String verifyCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User hospital;

    @OneToMany(mappedBy = "hospital")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PostFindDonator> post = new ArrayList<>();

    @OneToMany(mappedBy = "donator")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DonatedBlood> donated = new ArrayList<>();

    public List<PostFindDonator> getPost() {
        return this.post;
    }

    public void setPost(List<PostFindDonator> post) {
        this.post = post;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public User getHospital() {
        return this.hospital;
    }

    public void setHospital(User hospital) {
        this.hospital = hospital;
    }

    public List<DonatedBlood> getDonated() {
        return this.donated;
    }

    public void setDonated(List<DonatedBlood> donated) {
        this.donated = donated;
    }

    public Hospital() {
    }

    public Hospital(String name, String address, String verifyCode) {
        this.name = name;
        this.address = address;
        this.verifyCode = verifyCode;
    }

}
