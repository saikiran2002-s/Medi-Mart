package com.med.MediMart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.MediMart.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Integer> {
	
	Optional<Drug> findByDrugname(String drugname);
}
