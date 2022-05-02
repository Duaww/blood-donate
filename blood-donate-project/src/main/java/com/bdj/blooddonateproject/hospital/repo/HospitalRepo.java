package com.bdj.blooddonateproject.hospital.repo;

import java.util.Optional;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Long> {

    @Query(value = "SELECT h.* FROM v1.hospital h INNER JOIN v1.user u ON u.id = h.user_id WHERE u.username=:username", nativeQuery = true)
    Optional<Hospital> findInfoHospital(String username);

}