package com.ibm.academia.restapi.usuario.model.services;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;


public interface IUsuarioService{

	public ResponseEntity<?> buscarPerfilPorId(Integer perfilId);
	
	public ResponseEntity<?> obtenerTarjetas(@Valid @RequestBody UsuarioValidacion usuarioValidacion, BindingResult result);
	
	public ResponseEntity<?> metodoAlternativo();
	
}
