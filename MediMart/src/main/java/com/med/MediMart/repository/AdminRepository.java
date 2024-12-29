package com.med.MediMart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.med.MediMart.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
//	@Query("SELECT a FROM admin a")
//	List<Admin> findAllAdmins();

	Optional<Admin> findByAdminemail(String adminemail);

	Optional<Admin> findByAdminpassword(String adminpassword);

	Optional<Admin> findByAdminemailAndAdminpassword(String adminemailid, String adminpassword);

}
