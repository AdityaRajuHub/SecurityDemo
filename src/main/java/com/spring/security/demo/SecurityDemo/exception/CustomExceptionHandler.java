package com.spring.security.demo.SecurityDemo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest req, Exception ex) {
		
		System.out.println("Exception occured! Error: "+ex.getMessage());
		
		return "error";
	}
}
