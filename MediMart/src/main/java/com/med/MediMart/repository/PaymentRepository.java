package com.med.MediMart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.MediMart.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

