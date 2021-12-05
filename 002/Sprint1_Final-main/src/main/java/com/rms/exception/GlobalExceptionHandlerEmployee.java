package com.rms.exception;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandlerEmployee {
	
	//handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException
	(ResourceNotFoundException ex,WebRequest request)
	{
		
		ErrorDetailsEmployee errorDetails = new ErrorDetailsEmployee(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	//handle global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException
	(Exception ex,WebRequest request)
	{
		
		ErrorDetailsEmployee errordetails=new ErrorDetailsEmployee(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errordetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//To handle custom validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException ex){
		ErrorDetailsEmployee errordetails=new ErrorDetailsEmployee(new Date(), "Validation Error..!!  Check inputs again", 
				ex.getBindingResult().getFieldError().getDefaultMessage());
		
		
		return new ResponseEntity<>(errordetails,HttpStatus.BAD_REQUEST);
		
		
	}

}