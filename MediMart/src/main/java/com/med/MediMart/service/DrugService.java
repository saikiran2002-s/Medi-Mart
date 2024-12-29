package com.med.MediMart.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.med.MediMart.DAO.DrugDAO;
import com.med.MediMart.entity.Drug;
import com.med.MediMart.exception.NotFound;
import com.med.MediMart.util.SuccessResponse;

@Service
public class DrugService {
	
	@Autowired
	DrugDAO dao;
	
	@Autowired
	AdminService service;
	
	public SuccessResponse allSuccessResponse(Object obj, int stats, String message) {
		SuccessResponse data = SuccessResponse.builder().status(stats).dateTime(LocalDateTime.now()).data(obj)
				.msg(message).build();
		return data;
	}
	
	public ResponseEntity<SuccessResponse> saveDrug(int adminid,Drug drug) {
		if(service.fetchAdminById(adminid)!=null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(dao.saveDrug(drug), HttpStatus.CREATED.value(), "Drug Details saved successfull"),HttpStatus.CREATED);
		}
		else {
			throw new NotFound("Admin id " + adminid + " is not found");
		}
	}
	
	public ResponseEntity<SuccessResponse> updateDrug(int adminid,Drug drug) {
		if(service.fetchAdminById(adminid)!=null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(dao.updateDrug(drug), HttpStatus.CREATED.value(), "Drug Details updated successfull"),HttpStatus.CREATED);
		}
		else {
			throw new NotFound("Admin id " + adminid + " is not found");
		}	
	}
	
	public  ResponseEntity<SuccessResponse> deleteDrugInfo(int adminid,int id) {
		if(service.fetchAdminById(adminid)!=null) {
			return new ResponseEntity<SuccessResponse>(
					allSuccessResponse(dao.deleteDeleteInfo(id), HttpStatus.GONE.value(), id+" Drug Details are Deleted"),HttpStatus.GONE);
		}
		else {
			throw new NotFound("Admin id " + adminid + " is not found");
		}
	}
	
	public  ResponseEntity<SuccessResponse> fechById(int id){
		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(dao.fetchById(id), HttpStatus.FOUND.value(), "Drug Details Found successfull "+id),
				HttpStatus.FOUND);
	}
	
	public  ResponseEntity<SuccessResponse> fechByName(String name){
		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(dao.fetchByName(name), HttpStatus.FOUND.value(), "Drug Details Found successfull "+name),
				HttpStatus.FOUND);
	}
	
	public  ResponseEntity<SuccessResponse> fetchByAll(){
		return new ResponseEntity<SuccessResponse>(
				allSuccessResponse(dao.fetchByAll(), HttpStatus.FOUND.value(), "Drug Details Found successfull"),
				HttpStatus.FOUND);
	}
}

