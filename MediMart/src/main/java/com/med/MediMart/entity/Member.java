package com.med.MediMart.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberid;
	private String name;
	private String gender;
	private LocalDate dob;
	@Column(unique = true)
	private String email;
	private String password;
	@Lob
	@Column(columnDefinition ="LONGBLOB" ,length = Integer.MAX_VALUE)
	private byte[] image;
	private long mobileno;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private boolean disabled;
}
