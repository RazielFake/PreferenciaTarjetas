package com.ibm.academia.restapi.usuario.mapper;

import com.ibm.academia.restapi.usuario.model.dto.UsuarioDTO;
import com.ibm.academia.restapi.usuario.model.entities.Usuario;

public class UsuarioMapper {

	public static UsuarioDTO mapUsuario(Usuario usuario) {
		UsuarioDTO usuarioDTO = (UsuarioDTO) new UsuarioDTO();
		usuarioDTO.setEdad(usuario.getEdad());
		usuarioDTO.setSalario(usuario.getEdad());
		usuarioDTO.setPasion(usuario.getPasion());
		usuarioDTO.setTarjetas(usuario.getTarjetas());
		return usuarioDTO;
	}
	
}
