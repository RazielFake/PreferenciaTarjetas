package com.ibm.academia.restapi.usuario.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.usuario.exception.BadRequestException;
import com.ibm.academia.restapi.usuario.model.entities.Usuario;
import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;
import com.ibm.academia.restapi.usuario.model.enums.Pasion;

import brave.Tracer;


@Service
public class ValidacionServiceImpl implements IValidacionService {

	@Autowired
	private Tracer tracer;
	
	@Override
	public Usuario validarCampos(UsuarioValidacion usuarioValidacion) {
		Usuario usuario = new Usuario();
		try {
			usuario.setPasion(validarPasion(usuarioValidacion));
			usuario.setSalario(validarSalario(usuarioValidacion));
			usuario.setEdad(validarEdad(usuarioValidacion));
		}catch(BadRequestException e){
			throw e;
		}catch(NumberFormatException e) {
			throw e;
		}
		
		return usuario;
	}
	
	public Pasion validarPasion(UsuarioValidacion usuarioValidacion) {
		switch(usuarioValidacion.getPasion().toUpperCase()) {
		case "SHOPPING": 
			return Pasion.SHOPPING;
		case "TRAVELS":
			return Pasion.TRAVELS;
		case "HELP":
			return Pasion.HELP;
		case "MY_BUSSYNESS":
			return Pasion.MY_BUSSYNESS;
		case "SPORTS":
			return Pasion.SPORTS;
		case "MY_STYLE":
			return Pasion.MY_STYLE;
		default:
			tracer.currentSpan().tag(
					"error.mensaje", "Pasion: '" + usuarioValidacion.getPasion() + "' no se encuentra definida");
			throw new BadRequestException(
					"Pasion: '" + usuarioValidacion.getPasion() + "' no se encuentra definida");
		}
	}
	

	public Integer validarSalario(UsuarioValidacion usuarioValidacion) {
		Integer salario;
		try {
			salario = Integer.valueOf(usuarioValidacion.getSalario());
		}catch(NumberFormatException e) {
			throw new NumberFormatException(
					"El dato: '" + usuarioValidacion.getSalario() + "' es invalido.");
		}
		if(salario < 0 || salario < 7000) {
			throw new BadRequestException(
					"El salario debe ser positivo y debe ser mayor a $6,999.");
		}
		return salario;
	}
	
	public Integer validarEdad(UsuarioValidacion usuarioValidacion) {
		Integer edad;
		try {
			edad = Integer.valueOf(usuarioValidacion.getEdad());
		}catch(NumberFormatException e) {
			throw new NumberFormatException(
					"El dato: '" + usuarioValidacion.getEdad() + "' es invalido.");
		}
		if(edad < 0 || edad < 18) {
			throw new BadRequestException(
					"La edad debe ser positiva y debe ser mayor a 17.");
		}else if(edad > 75) {
			throw new BadRequestException(
					"La edad no puede ser mayor a 75.");
		}
		return edad;
	}

}
