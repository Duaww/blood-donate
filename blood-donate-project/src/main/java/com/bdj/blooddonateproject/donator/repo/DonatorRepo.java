package com.bdj.blooddonateproject.donator.repo;

import java.util.Optional;

import com.bdj.blooddonateproject.donator.model.Donator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonatorRepo extends JpaRepository<Donator, Long> {

    @Query(value = "SELECT d.* FROM v1.donator d INNER JOIN v1.user u ON u.id = d.user_id WHERE u.username=:username AND u.is_deleted = FALSE", nativeQuery = true)
    Optional<Donator> findInfoDonator(String username);

    @Query(value = "SELECT d.* FROM v1.donator d INNER JOIN v1.user u ON d.user_id=u.id WHERE u.is_deleted = FALSE", nativeQuery = true)
    Page<Donator> getListDonator(Pageable pageable);

    @Query(value = "SELECT COUNT(*) as number_donated FROM v1.donator d INNER JOIN v1.donated_blood dnt ON dnt.donator_id= d.id WHERE d.id= :donatorId", nativeQuery = true)
    Integer getNumDonatedOfUser(Long donatorId);

    @Query(value = "SELECT * FROM v1.donator d WHERE d.id = :donatorId", nativeQuery = true)
    Optional<Donator> findDonatorById(Long donatorId);
}
