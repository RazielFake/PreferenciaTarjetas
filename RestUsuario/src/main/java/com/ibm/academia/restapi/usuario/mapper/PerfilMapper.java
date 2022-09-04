package com.ibm.academia.restapi.usuario.mapper;

import com.ibm.academia.restapi.usuario.model.dto.PerfilDTO;
import com.ibm.academia.restapi.usuario.model.entities.Perfil;

public class PerfilMapper {

	public static PerfilDTO mapPerfil(Perfil perfil) {
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setId(perfil.getId());
		perfilDTO.setPasion(perfil.getPasion());
		perfilDTO.setSalarioMinimo(perfil.getSalarioMinimo());
		perfilDTO.setSalarioMaximo(perfil.getSalarioMaximo());
		perfilDTO.setEdadMinima(perfil.getEdadMinima());
		perfilDTO.setEdadMaxima(perfil.getEdadMaxima());
		perfilDTO.setTarjetas(perfil.getTarjetas());
		perfilDTO.setPuerto(perfil.getPuerto());
		return perfilDTO;
	}
	
}
