package com.med.MediMart.DAO;

import java.util.List;

import com.med.MediMart.entity.Member;

public interface MemberDAO {
	Member saveMember(Member member);

	List<Member> getAllMemberDetails();

	Member updateMemberInfo(Member member);

	Member deleteMemberInfo(int id);

	Member fetchMemberById(int id);

	Member verifymemberEmail(String email);

	Member verifyMemberPassword(String password);

	Member fetchmemberByEmailNadPasswordLogIn(String email, String password);
}
