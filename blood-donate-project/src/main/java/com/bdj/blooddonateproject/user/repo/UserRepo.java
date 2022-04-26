package com.bdj.blooddonateproject.user.repo;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.bdj.blooddonateproject.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM v1.user u WHERE u.is_deleted = false AND u.username =:username", nativeQuery = true)
    Optional<User> findByUsername(String username);
}
