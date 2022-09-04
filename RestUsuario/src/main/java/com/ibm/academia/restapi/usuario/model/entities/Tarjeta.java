package com.ibm.academia.restapi.usuario.model.entities;

import java.io.Serializable;

import com.ibm.academia.restapi.usuario.model.enums.NombreTarjeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tarjeta implements Serializable{
	
	private NombreTarjeta nombreTarjeta;
	
	private static final long serialVersionUID = 4339079030187244643L;
	
}
