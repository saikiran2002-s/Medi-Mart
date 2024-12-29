package com.med.MediMart.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.med.MediMart.entity.Admin;
import com.med.MediMart.entity.Member;
import com.med.MediMart.repository.AdminRepository;

@Repository
public class AdminDAOimpl implements AdminDAO {
	@Autowired
	AdminRepository repository;

	@Override
	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);
	}

	@Override
	public List<Admin> getAllDetails() {
		return repository.findAll();

	}

	@Override
	public Admin updateAdminInfo(Admin admin) {
//		repository.delete(admin);
		
		return repository.save(admin);

	}

	@Override
	public Admin deleteAdminInfo(int id) {
		Optional<Admin> optional = repository.findById(id);

//		repository.deleteById(id);
		if (optional.isPresent()) {
			Admin admin = optional.get();
			repository.delete(admin);
			return admin;
		}
		return null;

	}

	@Override
	public Admin fetchAdminByid(int id) {
		Optional<Admin> optional = repository.findById(id);
		if (optional.isPresent()) {
			Admin admin = optional.get();
			return admin;
		}
		return null;
	}

	@Override
	public Admin verifyEmail(String email) {
		Optional<Admin> opEmail = repository.findByAdminemail(email);
		if (opEmail.isPresent()) {
			Admin admin = opEmail.get();
			return admin;
		}

		return null;
	}

	@Override
	public Admin verifyPassword(String password) {
		Optional<Admin> oppass = repository.findByAdminpassword(password);
		if (oppass.isPresent()) {
			Admin admin = oppass.get();
			return admin;
		}
		return null;
	}

	@Override
	public Admin fetchAdminByEmailAndPassword(String email, String password) {
		Optional<Admin> ad = repository.findByAdminemailAndAdminpassword(email, password);

		verifyEmail(email);

		verifyPassword(password);
		if (ad.isPresent()) {
			Admin admin = ad.get();
			return admin;
		}
		return null;

	}

//	@Override
//	public Member verifyAdminAndMember(int adminId,int memberId) {
//		
//		return null;
//	}
	

}
