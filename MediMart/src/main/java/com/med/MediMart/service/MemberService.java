package com.med.MediMart.service;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.med.MediMart.DAO.MemberDAO;
import com.med.MediMart.entity.Member;
import com.med.MediMart.exception.EmailNotFoundException;
import com.med.MediMart.exception.IncorrectPasswordException;
import com.med.MediMart.exception.NotFound;
import com.med.MediMart.util.MediMartMailSender;
import com.med.MediMart.util.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MediMartMailSender mailSender;

	public SuccessResponse allSuccessResponse(Object obj, int stats, String message) {
		SuccessResponse data = SuccessResponse.builder().status(stats).dateTime(LocalDateTime.now()).data(obj)
				.msg(message).build();
		return data;
	}

	public ResponseEntity<SuccessResponse> saveMember(Member member) {
		Member db = memberDAO.saveMember(member);
		if (db != null) {
			try {
				mailSender.sendEmailToAdmins(db);
				mailSender.sendEmailToMembers(member);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(db, HttpStatus.CREATED.value(), "Member saved successfull"), HttpStatus.CREATED);

	}

	public ResponseEntity<SuccessResponse> getAllMemberDetails() {
		return new ResponseEntity<SuccessResponse>(allSuccessResponse(memberDAO.getAllMemberDetails(),
				HttpStatus.FOUND.value(), "All the Details of the Members"), HttpStatus.FOUND);

	}

	public ResponseEntity<SuccessResponse> fetchMemberById(int id) {
		Member db = memberDAO.fetchMemberById(id);

		if (db != null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(db, HttpStatus.FOUND.value(), " Member found successfully"), HttpStatus.FOUND);
		} else {

			throw new NotFound("Member Not found");
		}
	}

	public ResponseEntity<SuccessResponse> updateMemberInfo(Member member, int id) {
		member.setMemberid(id);
		;
		return new ResponseEntity<SuccessResponse>(allSuccessResponse(memberDAO.updateMemberInfo(member),
				HttpStatus.CREATED.value(), member.getMemberid() + " " + member.getName() + " is Updated"),
				HttpStatus.CREATED);

	}

	public ResponseEntity<SuccessResponse> deleteMemberInfo(int id) {

		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(memberDAO.deleteMemberInfo(id), HttpStatus.GONE.value(), id + " is Deleted"),
				HttpStatus.GONE);

	}

	public ResponseEntity<SuccessResponse> fetchByemailAndpassword(String email, String password) {

		Member db = memberDAO.fetchmemberByEmailNadPasswordLogIn(email, password);

		if (db != null) {
			return new ResponseEntity<>(
					allSuccessResponse(db, HttpStatus.FOUND.value(), email + " Member found. LogIn successful."),
					HttpStatus.FOUND);
		}

		if (memberDAO.verifymemberEmail(email) == null) {
			throw new EmailNotFoundException("Email Not Found" + " logIn failed");
		}

		if (memberDAO.verifyMemberPassword(password) == null) {
			throw new IncorrectPasswordException("Incorrect Password" + " logIn failed");
		}

		return null;
	}

	public ResponseEntity<SuccessResponse> uploadProfile(int memid,MultipartFile file) throws IOException{
		Member memberdb=memberDAO.fetchMemberById(memid);
		if(memberdb!=null) {
			memberdb.setImage(file.getBytes());
			
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(memberDAO.updateMemberInfo(memberdb), 
							HttpStatus.CREATED.value(),
							"Member saved successfull"),
					HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<byte[]> fetch(int memid) throws IOException{
		Member memberdb=memberDAO.fetchMemberById(memid);
		if(memberdb!=null) {
			byte[]data=memberdb.getImage();
			HttpHeaders header=new HttpHeaders();
			header.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<byte[]>(data,header,HttpStatus.FOUND);
		}
		return null;
	}
	
}
