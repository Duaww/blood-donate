package com.bdj.blooddonateproject.donator.dto;

import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.enums.GroupBlood;

public class DonatorDTO {

    private Long id;

    private String name;

    private Integer birthday;

    private String phone;

    private String email;

    private GroupBlood blood;

    private String idCard;

    private Integer numDonated;
    private Integer numOfNotDonated;

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

    public Integer getNumDonated() {
        return this.numDonated;
    }

    public void setNumDonated(Integer numDonated) {
        this.numDonated = numDonated;
    }

    public Integer getNumOfNotDonated() {
        return this.numOfNotDonated;
    }

    public void setNumOfNotDonated(Integer numOfNotDonated) {
        this.numOfNotDonated = numOfNotDonated;
    }

    public DonatorDTO() {
    }

    public DonatorDTO(Donator donator) {
        this.id = donator.getId();
        this.name = donator.getName();
        this.birthday = donator.getBirthday();
        this.phone = donator.getPhone();
        this.email = donator.getEmail();
        this.blood = donator.getBlood();
        this.idCard = donator.getIdCard();
        this.numOfNotDonated = donator.getNumOfNotDonated();
    }

    public DonatorDTO(Long id, String name, Integer birthday, String phone, String email, GroupBlood blood,
            String idCard, Integer numDonated, Integer numOfNotDonated) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.blood = blood;
        this.idCard = idCard;
        this.numDonated = numDonated;
        this.numOfNotDonated = numOfNotDonated;
    }

}
