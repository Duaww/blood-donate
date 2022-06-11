package com.bdj.blooddonateproject.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdj.blooddonateproject.admin.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

}
