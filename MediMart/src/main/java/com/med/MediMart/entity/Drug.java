package com.med.MediMart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Drug {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int drugid;
	private String drugname;
	private String drugcompany;
	private String drugtype;
	private double drugprice;
	private int drugquantity;
	private int drugrating;
	private boolean drugbanned;
	
}
