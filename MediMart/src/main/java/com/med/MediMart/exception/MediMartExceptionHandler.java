package com.med.MediMart.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.med.MediMart.util.SuccessResponse;

@RestControllerAdvice
public class MediMartExceptionHandler {
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlicve(SQLIntegrityConstraintViolationException e) {
		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.msg("You cant perform this operatioon").data(e.getMessage()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFound.class)
	public ResponseEntity<SuccessResponse> notFound(NotFound e) {
		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.NOT_FOUND.value())
				.msg("You can not perform this operation").data(e.getMsg()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<SuccessResponse> emailNotFound(EmailNotFoundException e) {
		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.NOT_FOUND.value())
				.msg("You can not perform this operation").data(e.getMsg()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<SuccessResponse> emailNotFound(IncorrectPasswordException e) {
		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
				.msg("You can not perform this operation").data(e.getMsg()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.NOT_ACCEPTABLE);
	}

}
