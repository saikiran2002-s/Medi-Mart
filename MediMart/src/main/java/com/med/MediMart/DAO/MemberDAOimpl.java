package com.med.MediMart.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.med.MediMart.entity.Member;
import com.med.MediMart.repository.MemberRepository;

@Repository
public class MemberDAOimpl implements MemberDAO {
	@Autowired
	MemberRepository repository;

	@Override
	public Member saveMember(Member member) {

		return repository.save(member);
	}

	@Override
	public List<Member> getAllMemberDetails() {
		return repository.findAll();
	}

	@Override
	public Member updateMemberInfo(Member member) {
		return repository.save(member);
	}

	@Override
	public Member deleteMemberInfo(int id) {
		Optional<Member> optional = repository.findById(id);
		if (optional.isPresent()) {
			Member member = optional.get();
			repository.delete(member);
			return member;
		}
		return null;
	}

	@Override
	public Member fetchMemberById(int id) {
		Optional<Member> optional = repository.findById(id);
		if (optional.isPresent()) {
			Member member = optional.get();
			return member;
		}
		return null;
	}

	@Override
	public Member verifymemberEmail(String email) {
		Optional<Member> opEmail = repository.findByEmail(email);
		if (opEmail.isPresent()) {
			Member member = opEmail.get();
			return member;
		}

		return null;
	}

	@Override
	public Member verifyMemberPassword(String password) {
		Optional<Member> oppass = repository.findByPassword(password);
		if (oppass.isPresent()) {
			Member member = oppass.get();
			return member;
		}
		return null;
	}

	@Override
	public Member fetchmemberByEmailNadPasswordLogIn(String email, String password) {
		Optional<Member> ad = repository.findByEmailAndPassword(email, password);

		verifymemberEmail(email);

		verifyMemberPassword(password);
		if (ad.isPresent()) {
			Member member = ad.get();
			return member;
		}
		return null;
	}

}
