package com.ibm.academia.restapi.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.model.entities.Perfil;
import com.ibm.academia.restapi.model.enums.Pasion;

@Repository("repositorioPerfiles")
public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Integer>{

	public Iterable<Perfil> findPerfilesByPasion(Pasion pasion);
	
	public Iterable<Perfil> findPerfilesBySalarioMinimoAfter(Double salario);
	
}
