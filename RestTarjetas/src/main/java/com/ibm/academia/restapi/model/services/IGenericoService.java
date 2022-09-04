package com.ibm.academia.restapi.model.services;

import java.util.List;
import java.util.Optional;

public interface IGenericoService<E> {

	public Optional<E> buscarPorId(Integer id);
	public E guardar(E entidad);
	public List<E> findAll();
	public void eliminarPorId(Integer id);
	
}
