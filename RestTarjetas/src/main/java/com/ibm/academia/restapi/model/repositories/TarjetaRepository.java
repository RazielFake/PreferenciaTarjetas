package com.ibm.academia.restapi.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.model.entities.Tarjeta;

@Repository("repositorioTarjetas")
public interface TarjetaRepository extends PagingAndSortingRepository<Tarjeta, Integer>{

}
