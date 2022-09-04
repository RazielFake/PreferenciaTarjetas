package com.ibm.academia.restapi.usuario.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.ibm.academia.restapi.usuario.model.enums.Pasion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil implements Serializable {

	private Integer id;
	
	private Pasion pasion;
	
	private Double salarioMinimo;
	
	private Double salarioMaximo;
	
	private Integer edadMinima;
	
	private Integer edadMaxima;
	
	private Set<Tarjeta> tarjetas;
	
	private Date fechaAlta;
	
	private Date fechaModificacion;
	
	private Integer puerto;
	
	private static final long serialVersionUID = 8408738505967247830L;

}
