package com.med.MediMart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.MediMart.service.PaymentService;
import com.med.MediMart.util.SuccessResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@PostMapping("/savePaymentDetails")
	public ResponseEntity<SuccessResponse> save(@RequestParam int memid,@RequestBody List<String> drugname){
		return service.saveDetails(memid, drugname);
	}
	
}
