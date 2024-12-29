package com.med.MediMart.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.MediMart.entity.Drug;
import com.med.MediMart.service.DrugService;
import com.med.MediMart.util.SuccessResponse;

@RestController
@RequestMapping("/drug")
public class DrugController {
	
	@Autowired
	DrugService service;

	
	@PostMapping("/savedrugdetails")
	public ResponseEntity<SuccessResponse> saveDrug(@RequestParam int adminid,@RequestBody Drug drug) {
			return service.saveDrug(adminid,drug);
	}
	
	@PostMapping("/updatedrugdetails")
	public ResponseEntity<SuccessResponse> updateDrug(@RequestParam int adminid,@RequestBody Drug drug) {
			return service.updateDrug(adminid,drug);
	}
	
	@DeleteMapping("/deleteDrug")
	public ResponseEntity<SuccessResponse> deleteDrugInfo(@RequestParam int id,int adminid) {
			return service.deleteDrugInfo(adminid,id);
	}
	
	@GetMapping("/id")
	public ResponseEntity<SuccessResponse> fetchById(@RequestParam int id){
		return service.fechById(id);
	}
	
	@PostMapping("/alldetails")
	public ResponseEntity<SuccessResponse> fetchByAll(){
		return service.fetchByAll();
	}
	
	@GetMapping("/byname")
	public ResponseEntity<SuccessResponse> fetchByName(@RequestParam String name){
		return service.fechByName(name);
	}
}
