package com.med.MediMart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.MediMart.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findByPassword(String password);

	Optional<Member> findByEmailAndPassword(String email, String password);

}
