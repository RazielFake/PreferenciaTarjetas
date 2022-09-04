package com.ibm.academia.restapi.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.exception.BadRequestException;
import com.ibm.academia.restapi.exception.NotFoundException;



@ControllerAdvice
public class TarjetasRestException {

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> noExisteException(NotFoundException exception){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<?> NumeroFormatoInvalidoException(NumberFormatException exception){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.I_AM_A_TEAPOT); 
	}
	
}
