package com.ibm.academia.restapi.usuario.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.usuario.exception.BadRequestException;
import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;
import com.ibm.academia.restapi.usuario.model.services.IUsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	@Qualifier("serviceFeign")
	private IUsuarioService usuarioService;
	
	
	/**
	 * Endpoint para obtener tarjetas correspondientes a un perfil dado
	 * @param usuarioValidacion Datos del usuario para obtenr tarjetas 
	 * @BadRequestException En caso de que se ingrese un dato incorrecto
	 * @NumberFormatException En caso de que se ingrese un dato con formato incorrecto
	 * @param result
	 * @return Tarjetas correspondientes
	 * @author BRPI 26/05/22
	 */
	//@CircuitBreaker(name = "usuarios", fallbackMethod = "metodoAlternativo")
	@PostMapping
	public ResponseEntity<?> obtenerTarjetas(@Valid @RequestBody UsuarioValidacion usuarioValidacion, BindingResult result){
		try {
			return this.usuarioService.obtenerTarjetas(usuarioValidacion, result);
		}catch(BadRequestException e) {
			logger.info("Message: " + e.getMessage() + ". Cause: " +e.getCause());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(NumberFormatException e) {
			logger.info("Message: " + e.getMessage() + ". Cause: " +e.getCause());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping("/metodo-alternativo")
	public ResponseEntity<?> metodoAlternativo(){
		return this.usuarioService.metodoAlternativo();
	}
	
}
