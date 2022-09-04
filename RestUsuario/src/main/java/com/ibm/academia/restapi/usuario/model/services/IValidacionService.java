package com.ibm.academia.restapi.usuario.model.services;


import com.ibm.academia.restapi.usuario.model.entities.Usuario;
import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;



public interface IValidacionService {

	public Usuario validarCampos(UsuarioValidacion usuarioValidacion);
	
}
