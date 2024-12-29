package com.med.MediMart.controller;

import org.springframework.web.bind.annotation.RestController;

import com.med.MediMart.entity.Admin;
import com.med.MediMart.service.AdminService;
import com.med.MediMart.service.MemberService;
import com.med.MediMart.util.MediMartMailSender;
import com.med.MediMart.util.SuccessResponse;
import com.zaxxer.hikari.util.SuspendResumeLock;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService service;
	@Autowired
	MemberService memberService;
	@Autowired
	MediMartMailSender mailSender;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Admin admin) {

		return service.saveAdmin(admin);

	}

	@PostMapping("/allDetails")
	public ResponseEntity<SuccessResponse> getallDetails() {

		return service.getAllAdminDetails();
	}

	@GetMapping("/fethchById")
	public ResponseEntity<SuccessResponse> fetchById(@RequestParam int id) {

		return service.fetchAdminById(id);
	}

	@PostMapping("/adminInfoUpdate")
	public ResponseEntity<SuccessResponse> updateAdminInfo(@RequestBody Admin admin, int id) {
		return service.updateAdminInfo(admin, id);

	}

	@PostMapping("/deleteAdmin")
	public ResponseEntity<SuccessResponse> deleteAdmin(int id) {
		return service.deleteAdminInfo(id);

	}

	@GetMapping("/adminlogIn")
	public ResponseEntity<SuccessResponse> adminLogIn(@RequestParam String email, String password) {

		return service.fetchByemailAndpassword(email, password);
	}

	@GetMapping("/checkAdminAndMember")
	public ResponseEntity<SuccessResponse> CheckAdminAndMemmber(@RequestParam int adminId, @RequestParam int memberId) {

		return service.enableTheMemberDisable(adminId, memberId);

	}
//	@GetMapping("/sendMail")
//	public void sendMail() {
//		try {
//			mailSender.sendimagemail();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
