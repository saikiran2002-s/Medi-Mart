package com.med.MediMart.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SuccessResponse {
	
	private int status;
	private String msg;
	private LocalDateTime dateTime;
	private Object data;
	
}
