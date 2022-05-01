package com.bdj.blooddonateproject.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.user.model.User;

@Entity
@Table(name = "admin", schema = "v1")
public class Admin {

    @Id
    @GeneratedValue(generator = "admin_generator")
    @SequenceGenerator(name = "admin_generator", sequenceName = "admin_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User admin;

    public User getAdmin() {
        return this.admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admin() {
    }

}
