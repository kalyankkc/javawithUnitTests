package com.mindtree.EmloyeeManagement.controller.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mindtree.EmloyeeManagement.exception.EmployeeManagementAppException;

@RestControllerAdvice
public class EmployeeManagementHandler {

	@ExceptionHandler(EmployeeManagementAppException.class)
	public ResponseEntity<Map<String, Object>> ServiceExceptionHandler(Exception e, Throwable cause) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "Something went wrong");
		response.put("ERROR", true);
		response.put("body", e.getMessage());
		response.put("HTTP STATUS", HttpStatus.NOT_FOUND);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "A bad reqqqest");
		response.put("ERROR", true);
		response.put("body", "Request not allowed");
		response.put("HTTP STATUS", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, Object>> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e, WebRequest request) {

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "A bad reqqqest");
		response.put("ERROR", true);
		response.put("body", "Method not allowed.. enter valid request");
		response.put("HTTP STATUS", HttpStatus.METHOD_NOT_ALLOWED);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

	}

}
