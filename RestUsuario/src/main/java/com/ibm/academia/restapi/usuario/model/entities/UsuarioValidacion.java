package com.ibm.academia.restapi.usuario.model.entities;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioValidacion implements Serializable{

	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacío")
	private String pasion;
	
	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacío")
	private String salario;
	
	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacío")
	private String edad;
	
	
	private static final long serialVersionUID = 3776634345728658870L;

}
