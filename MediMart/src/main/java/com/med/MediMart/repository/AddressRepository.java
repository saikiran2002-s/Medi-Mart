package com.med.MediMart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.MediMart.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
