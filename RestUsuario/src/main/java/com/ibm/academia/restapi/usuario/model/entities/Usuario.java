package com.ibm.academia.restapi.usuario.model.entities;

import java.io.Serializable;
import java.util.Set;

import com.ibm.academia.restapi.usuario.model.enums.Pasion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements Serializable{

	private Pasion pasion;
	
	private Integer salario;
	
	private Integer edad;
	
	private Set<Tarjeta> tarjetas;
	
	public Usuario(Pasion pasion, Integer salario, Integer edad) {
		this.pasion = pasion;
		this.salario = salario;
		this.edad = edad;
	}

	private static final long serialVersionUID = -4781079037764108422L;
	
}
