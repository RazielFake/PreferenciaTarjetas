package com.ibm.academia.restapi.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.exception.BadRequestException;
import com.ibm.academia.restapi.exception.NotFoundException;

public class GenericoServiceImpl<E, R extends PagingAndSortingRepository<E, Integer>> implements IGenericoService<E> {

	protected final R repository;
	
	public GenericoServiceImpl(R repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> buscarPorId(Integer id) {
		if(!this.repository.findById(id).isPresent())
			throw new NotFoundException("Este elemento no existe");
		else 
			return this.repository.findById(id);
	}

	@Override
	@Transactional
	public E guardar(E entidad) {
		return this.repository.save(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		List<E> listAll = (List<E>)this.repository.findAll();
		if(listAll.isEmpty())
			throw new BadRequestException("No hay elementos en la base de datos.");
		return listAll;
	}

	@Override
	@Transactional
	public void eliminarPorId(Integer id) {
		this.repository.deleteById(id);
	}

}
