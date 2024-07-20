package com.restapi.learnrestapispring;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomisedResponseEntityErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
				"Total Errors: " + ex.getErrorCount() + ", First Error: " + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);

	}
}
