package com.bdj.blooddonateproject.donated_blood.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.hospital.model.Hospital;

@Entity
@Table(name = "donated_blood", schema = "v1")
public class DonatedBlood {
    @Id
    @GeneratedValue(generator = "donated_generator")
    @SequenceGenerator(name = "donated_generator", sequenceName = "donated_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donator_id", nullable = false)
    private Donator donator;

    private Integer timeDonated;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getTimeDonated() {
        return this.timeDonated;
    }

    public void setTimeDonated(Integer timeDonated) {
        this.timeDonated = timeDonated;
    }

    public DonatedBlood() {
    }

    public DonatedBlood(Integer timeDonated) {
        this.timeDonated = timeDonated;
    }

}
