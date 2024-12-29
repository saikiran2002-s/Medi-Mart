package com.med.MediMart.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.med.MediMart.entity.Payment;
import com.med.MediMart.repository.PaymentRepository;

@Repository
public class PaymentDAO {
	
	@Autowired
	PaymentRepository repository;
	
	public Payment savePaymentDetails(Payment payment) {
		return repository.save(payment);
	}
	
}
