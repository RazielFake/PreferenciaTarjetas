package com.ibm.academia.restapi.model.services;

import java.util.List;


import com.ibm.academia.restapi.model.entities.Perfil;
import com.ibm.academia.restapi.model.enums.Pasion;

public interface IPerfilService extends IGenericoService<Perfil>{

	public List<Perfil> buscarPerfilesPorPasion(Pasion pasion);
	
}
