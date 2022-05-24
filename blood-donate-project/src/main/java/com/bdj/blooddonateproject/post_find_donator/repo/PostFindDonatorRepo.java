package com.bdj.blooddonateproject.post_find_donator.repo;

import java.util.Optional;
import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFindDonatorRepo extends JpaRepository<PostFindDonator, Long> {

    @Query(value = "SELECT * FROM v1.post_find_donator p WHERE p.is_deleted = FALSE AND p.hospital_id = :id ORDER BY p.update_at DESC", nativeQuery = true)
    Page<PostFindDonator> listPostFindDonator(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM v1.post_find_donator WHERE id = :id AND is_deleted = FALSE", nativeQuery = true)
    Optional<PostFindDonator> getPostFindDonator(Long id);

}
