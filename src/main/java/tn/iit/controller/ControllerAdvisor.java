package tn.iit.controller;

import java.time.LocalDateTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import exception.CompteNotFoundException;

@ControllerAdvice
	public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CompteNotFoundException.class)
	public String handleCompteNotFoundException(CompteNotFoundException ex, Model model) {
		model.addAttribute("timestamp", LocalDateTime.now());
		model.addAttribute("message", ex.getMessage());
		return "404";
}

@ExceptionHandler(Exception.class)
	public String handleException(CompteNotFoundException ex, Model model) {
		model.addAttribute("timestamp", LocalDateTime.now());
		model.addAttribute("message", ex.getMessage());
		return "404";
	}
}