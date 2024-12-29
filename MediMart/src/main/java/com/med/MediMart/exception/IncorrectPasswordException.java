package com.med.MediMart.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectPasswordException extends RuntimeException {
	String msg;
}
