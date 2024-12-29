package com.med.MediMart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.med.MediMart.DAO.DrugDAO;
import com.med.MediMart.DAO.MemberDAO;
import com.med.MediMart.DAO.PaymentDAO;
import com.med.MediMart.entity.Drug;
import com.med.MediMart.entity.Member;
import com.med.MediMart.entity.Payment;
import com.med.MediMart.exception.NotFound;
import com.med.MediMart.util.SuccessResponse;

@Service
public class PaymentService {

	@Autowired
	PaymentDAO dao;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	DrugDAO drugDAO;
	
	Payment payment;
	
	public SuccessResponse allSuccessResponse(Object obj, int status, String message) {
		SuccessResponse data = SuccessResponse.builder().status(status).dateTime(LocalDateTime.now()).data(obj)
				.msg(message).build();
		return data;
	}
	
	public ResponseEntity<SuccessResponse> saveDetails(int memid,List<String> drugname){
		Member memberDB=memberDAO.fetchMemberById(memid);
		double totalAmount=0;
		List<Drug> list=new ArrayList<Drug>();
		if(memberDB!=null) {
			for(String drug:drugname) {
				Drug drugDB=drugDAO.fetchByName(drug);
				if(drugDB!=null) {
					if(drugDB.getDrugquantity()!=0) {
						totalAmount+=drugDB.getDrugprice();
						list.add(drugDB);
					}else {
						throw new NotFound("Drug Not Found");
					}
				}else {
					throw new NotFound("Drug Not Found");
				}
			}
			Payment payment=new Payment();
			payment.setDrugs(list);
			payment.setMemberid(memid);
			payment.setOrderamount(totalAmount);
			return new ResponseEntity<SuccessResponse>(allSuccessResponse(dao.savePaymentDetails(payment),HttpStatus.CREATED.value(),"Oder Sucessfully"),HttpStatus.CREATED);
		}else {
			throw new NotFound("Member Id is Not Found");
		}
	}
	
}
