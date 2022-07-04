package com.bdj.blooddonateproject.donated_blood.repo;

import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;
import com.bdj.blooddonateproject.enums.GroupBlood;

import java.util.List;

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

    @Query(value = "SELECT dnb.* FROM v1.donated_blood dnb INNER JOIN v1.donator d ON d.id = dnb.donator_id WHERE dnb.hospital_id = :hospitalId AND d.name ILIKE %:nameFilter% AND d.blood IN (:groupBlood) AND d.id_card ILIKE %:idCardFilter%", nativeQuery = true)
    Page<DonatedBlood> getListDonatedWithFilter(Long hospitalId, String nameFilter, List<String> groupBlood,
            String idCardFilter,
            Pageable pageable);

}
