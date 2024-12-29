package com.med.MediMart.DAO;

import java.util.List;

import com.med.MediMart.entity.Admin;
import com.med.MediMart.entity.Member;

public interface AdminDAO {

	Admin saveAdmin(Admin admin);

	List<Admin> getAllDetails();

	Admin fetchAdminByid(int id);

	Admin updateAdminInfo(Admin admin);

	Admin deleteAdminInfo(int id);

	Admin verifyEmail(String email);

	Admin verifyPassword(String password);

	Admin fetchAdminByEmailAndPassword(String email, String password);
	
//	Member verifyAdminAndMember(int adminId,int memberId);
	
}
