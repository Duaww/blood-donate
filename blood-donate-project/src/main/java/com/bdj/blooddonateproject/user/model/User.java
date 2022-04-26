package com.bdj.blooddonateproject.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.admin.model.Admin;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.enums.RoleEnum;
import com.bdj.blooddonateproject.hospital.model.Hospital;

@Entity
@Table(name = "user", schema = "v1")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String uuid;
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;
    private Boolean isDeleted;

    @OneToOne(mappedBy = "hospital")
    private Hospital hospital;

    public Hospital getHospital() {
        return this.hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Donator getDonator() {
        return this.donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

    @OneToOne(mappedBy = "donator")
    private Donator donator;

    @OneToOne(mappedBy = "admin")
    private Admin admin;

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return this.role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Boolean isIsDeleted() {
        return this.isDeleted;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User() {
    }

    public User(String uuid, String username, String password, RoleEnum role, Boolean isDeleted) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
    }

}
