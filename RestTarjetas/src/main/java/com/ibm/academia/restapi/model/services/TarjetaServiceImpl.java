package com.ibm.academia.restapi.model.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.model.entities.Tarjeta;
import com.ibm.academia.restapi.model.repositories.TarjetaRepository;

@Service
public class TarjetaServiceImpl extends GenericoServiceImpl<Tarjeta, TarjetaRepository> implements ITarjetaService{

	@Autowired
	public TarjetaServiceImpl(TarjetaRepository repository) {
		super(repository);
	}
	

}
