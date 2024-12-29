package com.med.MediMart.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.med.MediMart.DAO.AdminDAO;
import com.med.MediMart.DAO.MemberDAO;
import com.med.MediMart.entity.Admin;
import com.med.MediMart.entity.Member;
import com.med.MediMart.exception.EmailNotFoundException;
import com.med.MediMart.exception.IncorrectPasswordException;
import com.med.MediMart.exception.NotFound;
import com.med.MediMart.util.MediMartMailSender;
import com.med.MediMart.util.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class AdminService {
	@Autowired
	AdminDAO adminDao;

	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	MediMartMailSender mailSender;

	public SuccessResponse allSuccessResponse(Object obj, int stats, String message) {
		SuccessResponse data = SuccessResponse.builder().status(stats).dateTime(LocalDateTime.now()).data(obj)
				.msg(message).build();
		return data;
	}

	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin) {

		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(adminDao.saveAdmin(admin), HttpStatus.CREATED.value(), "admin saved successfull"),
				HttpStatus.CREATED);
	}

	public ResponseEntity<SuccessResponse> getAllAdminDetails() {
		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(adminDao.getAllDetails(), HttpStatus.FOUND.value(), "All the Details of the Admins"),
				HttpStatus.FOUND);

	}

	public ResponseEntity<SuccessResponse> fetchAdminById(int id) {
		Admin db = adminDao.fetchAdminByid(id);

		if (db != null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(db, HttpStatus.FOUND.value(), " Admin found successfully"), HttpStatus.FOUND);
		} else {

			throw new NotFound("Admin Not found");
		}
	}

	public ResponseEntity<SuccessResponse> updateAdminInfo(Admin admin, int id) {
		admin.setId(id);
		return new ResponseEntity<SuccessResponse>(allSuccessResponse(adminDao.updateAdminInfo(admin),
				HttpStatus.CREATED.value(), admin.getId() + " " + admin.getAdminname() + " is Updated"),
				HttpStatus.CREATED);

	}

	public ResponseEntity<SuccessResponse> deleteAdminInfo(int id) {

		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(adminDao.deleteAdminInfo(id), HttpStatus.GONE.value(), id + " is Deleted"),
				HttpStatus.GONE);

	}

	public ResponseEntity<SuccessResponse> enableTheMemberDisable(int adminId, int memberId) {
		Admin db = adminDao.fetchAdminByid(adminId);
		;
		if (db != null) {
			Member memberdb = memberDAO.fetchMemberById(memberId);
			if (memberdb != null) {
				memberdb.setDisabled(true);
				memberDAO.updateMemberInfo(memberdb);
				try {
					mailSender.sendVerifyEmailToTheMember(memberdb);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ResponseEntity<SuccessResponse>(
						allSuccessResponse(memberdb, HttpStatus.FOUND.value(), " Member enabled successfully"),
						HttpStatus.FOUND);
			} else {

				throw new NotFound("member id " + memberId + " is not found");
			}
		} else {
			throw new NotFound("Admin id " + adminId + " is not found");
		}

	}

	public ResponseEntity<SuccessResponse> fetchByemailAndpassword(String email, String password) {

		Admin db = adminDao.fetchAdminByEmailAndPassword(email, password);

		if (db != null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(db, HttpStatus.FOUND.value(), email + " Admin found. LogIn successful."),
					HttpStatus.FOUND);
		}

		if (adminDao.verifyEmail(email) == null) {
			throw new EmailNotFoundException("Email Not Found" + " logIn failed");
		}

		if (adminDao.verifyPassword(password) == null) {
			throw new IncorrectPasswordException("Incorrect Password" + " logIn failed");
		}

		return null;
	}

}
