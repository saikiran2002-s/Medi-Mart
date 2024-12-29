package com.med.MediMart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.med.MediMart.entity.Member;
import com.med.MediMart.service.MemberService;
import com.med.MediMart.util.SuccessResponse;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;

	@PostMapping("/memberRegister")
	public ResponseEntity<SuccessResponse> saveMember(@RequestBody Member member) {

		return service.saveMember(member);
	}

	@PostMapping("/allMemberDetails")
	public ResponseEntity<SuccessResponse> getallDetails() {

		return service.getAllMemberDetails();
	}

	@GetMapping("/fethchBymemberId")
	public ResponseEntity<SuccessResponse> fetchById(@RequestParam int id) {

		return service.fetchMemberById(id);
	}

	@PostMapping("/memberInfoUpdate")
	public ResponseEntity<SuccessResponse> updateMemberInfo(@RequestBody Member member, int id) {
		return service.updateMemberInfo(member, id);

	}

	@PostMapping("/deleteMember")
	public ResponseEntity<SuccessResponse> deleteMember(int id) {
		return service.deleteMemberInfo(id);

	}

	@GetMapping("/memberlogIn")
	public ResponseEntity<SuccessResponse> memberLogIn(@RequestParam String email, String password) {

		return service.fetchByemailAndpassword(email, password);
	}
	
	@PutMapping("/upload")
	public ResponseEntity<SuccessResponse> upload(@RequestParam int memid,MultipartFile file) throws IOException{
		return service.uploadProfile(memid, file);
	}
	
	@GetMapping("/getphoto")
	public ResponseEntity<byte[]> upload(@RequestParam int memid) throws IOException{
		return service.fetch(memid);
	}
}
