package com.bdj.blooddonateproject.register_to_donate.repo;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT rtd.* FROM  v1.register_to_donate rtd INNER JOIN v1.donator d ON d.id = rtd.donator_id WHERE rtd.post_id = :postId AND d.name ILIKE %:nameFilter% AND d.id_card ILIKE %:idCardFilter% AND d.blood IN (:groupBlood) ORDER BY time_register DESC", nativeQuery = true)
    Page<RegisterToDonate> getListDonatedWithFilter(Long postId, String nameFilter, List<String> groupBlood,
            String idCardFilter,
            Pageable pageable);

    @Query(value = "SELECT rtd.* FROM v1.register_to_donate rtd WHERE donator_id = :donatorId AND post_id = :postId", nativeQuery = true)
    RegisterToDonate getRegisterByPostAndDonator(Long donatorId, Long postId);

}
