package com.bdj.blooddonateproject.donator.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;
import com.bdj.blooddonateproject.enums.GroupBlood;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;
import com.bdj.blooddonateproject.user.model.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "donator", schema = "v1")
public class Donator {
    @Id
    @GeneratedValue(generator = "donator_generator")
    @SequenceGenerator(name = "donator_generator", sequenceName = "donator_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;

    private Integer birthday;

    private String phone;

    @Column(unique = true)
    private String email;

    private GroupBlood blood;

    private String idCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User donator;

    @OneToMany(mappedBy = "donator")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<RegisterToDonate> register = new ArrayList<>();

    @OneToMany(mappedBy = "donator", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DonatedBlood> donated = new ArrayList<>();

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GroupBlood getBlood() {
        return this.blood;
    }

    public void setBlood(GroupBlood blood) {
        this.blood = blood;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public User getDonator() {
        return this.donator;
    }

    public void setDonator(User donator) {
        this.donator = donator;
    }

    public List<DonatedBlood> getDonated() {
        return this.donated;
    }

    public void setDonated(List<DonatedBlood> donated) {
        this.donated = donated;
    }

    public Donator() {
    }

    public Donator(String name, Integer birthday, String phone, String email, GroupBlood blood, String idCard) {
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.blood = blood;
        this.idCard = idCard;
    }

}
