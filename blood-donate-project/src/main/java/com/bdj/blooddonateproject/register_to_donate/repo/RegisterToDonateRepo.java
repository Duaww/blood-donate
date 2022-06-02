package com.bdj.blooddonateproject.register_to_donate.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterToDonateRepo extends JpaRepository<RegisterToDonate, Long> {

    @Query(value = "SELECT * FROM v1.register_to_donate WHERE post_id = :postId ORDER BY time_register DESC", nativeQuery = true)
    Page<RegisterToDonate> listDonatorRegisterByPostId(Long postId, Pageable pageable);

    @Query(value = "SELECT * FROM v1.register_to_donate WHERE post_id = :postId AND donator_id = :donatorId", nativeQuery = true)
    Optional<RegisterToDonate> getRegisterByPostAndDonatorId(Long postId, Long donatorId);

    @Query(value = "SELECT * FROM v1.register_to_donate WHERE post_id = :postId AND donator_id = :donatorId", nativeQuery = true)
    RegisterToDonate getRegister(Long postId, Long donatorId);

}
