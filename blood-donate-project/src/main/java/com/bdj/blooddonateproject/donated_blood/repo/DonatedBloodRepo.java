package com.bdj.blooddonateproject.donated_blood.repo;

import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonatedBloodRepo extends JpaRepository<DonatedBlood, Long> {

    @Query(value = "SELECT * FROM v1.donated_blood WHERE hospital_id = :hospitalId", nativeQuery = true)
    Page<DonatedBlood> getListDonatorByHospitalId(Long hospitalId, Pageable pageable);

    @Query(value = "SELECT * FROM v1.donated_blood WHERE donator_id = :donatorId ORDER BY time_donated DESC", nativeQuery = true)
    Page<DonatedBlood> getHistoryDonated(Long donatorId, Pageable pageable);

}
