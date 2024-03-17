package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(
			ArithmeticException e, WebRequest request) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setError(e.getMessage());
		List<String> detail = new ArrayList<String>();
		detail.add("Số nguyên không chia cho 0 được");
		errorResponse.setDetail(detail);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleFieldRequiredException(
			FieldRequiredException e, WebRequest request) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setError(e.getMessage());
		List<String> detail = new ArrayList<String>();
		detail.add("Trường name hoặc addresss chưa được gửi về");
		errorResponse.setDetail(detail);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
