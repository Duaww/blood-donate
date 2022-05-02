package com.bdj.blooddonateproject.donator.repo;

import java.util.Optional;

import com.bdj.blooddonateproject.donator.model.Donator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonatorRepo extends JpaRepository<Donator, Long> {

    @Query(value = "SELECT d.* FROM v1.donator d INNER JOIN v1.user u ON u.id = d.user_id WHERE u.username=:username", nativeQuery = true)
    Optional<Donator> findInfoDonator(String username);
}
