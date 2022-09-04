package com.ibm.academia.restapi.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.model.entities.Perfil;
import com.ibm.academia.restapi.model.enums.Pasion;
import com.ibm.academia.restapi.model.repositories.PerfilRepository;

@Service
public class PerfilServiceImpl extends GenericoServiceImpl<Perfil, PerfilRepository> implements IPerfilService{

	@Autowired
	private Environment enviroment;
	
	@Autowired
	public PerfilServiceImpl(PerfilRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Perfil> buscarPerfilesPorPasion(Pasion pasion) {
		List<Perfil> perfiles = (List<Perfil>) this.repository.findPerfilesByPasion(pasion);
		perfiles.forEach(
				perfil -> perfil.setPuerto(Integer.parseInt(enviroment.getProperty("local.server.port"))));
		return perfiles;
	}
	

}
