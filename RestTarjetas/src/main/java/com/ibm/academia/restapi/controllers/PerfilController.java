package com.ibm.academia.restapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.exception.BadRequestException;
import com.ibm.academia.restapi.exception.NotFoundException;
import com.ibm.academia.restapi.mapper.PerfilMapper;
import com.ibm.academia.restapi.model.dto.PerfilDTO;
import com.ibm.academia.restapi.model.entities.Perfil;
import com.ibm.academia.restapi.model.services.IPerfilService;
import com.ibm.academia.restapi.model.services.IValidacionService;


@RestController
@RequestMapping("/perfiles")
public class PerfilController {

	private final static Logger logger = LoggerFactory.getLogger(PerfilController.class);

	@Autowired
	private IPerfilService perfilService;
	
	@Autowired
	private IValidacionService validacionService;
	
	/**
	 * Endpoint para listar los perfiles en la base de datos
	 * @BadRequestException En caso de que no haya ningun perfil en la base de datos
	 * @return Lista con todos los eprfiles en la base de datos
	 * @author BRPI 23/05/22
	 */
	@GetMapping("/listar")
	public ResponseEntity<?> consultarTodosPerfiles(){
		List<Perfil> usuarios = null;
		try {
			usuarios = this.perfilService.findAll();
		}catch(BadRequestException e) {
			logger.info("Message: " + e.getMessage() + ". Cause: " +e.getCause());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		List<PerfilDTO> listUsuarios = usuarios
				.stream()
				.map(PerfilMapper::mapPerfil)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PerfilDTO>>(listUsuarios, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/perfil/perfilId/{perfilId}")
	public ResponseEntity<?> buscarPerfil(@PathVariable Integer perfilId){
		Optional<Perfil> oPerfil;
		try {
			oPerfil = this.perfilService.buscarPorId(perfilId);
		}catch(NotFoundException e) {
			logger.info("Message: " + e.getMessage() + ". Cause: " +e.getCause());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Perfil>(oPerfil.get(), HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para consultar los perfiles por pasion
	 * @param pasion Pasion que se desea consultar
	 * @return Lista con los perfiles
	 * @author BRPI 24/05/22 
	 */
	@GetMapping("/perfil-pasion/pasion/{pasion}")
	public ResponseEntity<?> buscarPerfilesPorPasion(@PathVariable String pasion){
		List<Perfil> usuarios = null;
		try {
			usuarios = this.perfilService.buscarPerfilesPorPasion(this.validacionService.obtenerPasion(pasion));
		}catch(BadRequestException e) {
			logger.info("Message: " + e.getMessage() + ". Cause: " +e.getCause());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		List<PerfilDTO> listUsuarios = usuarios
				.stream()
				.map(PerfilMapper::mapPerfil)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PerfilDTO>>(listUsuarios, HttpStatus.OK);
	}
	
}
