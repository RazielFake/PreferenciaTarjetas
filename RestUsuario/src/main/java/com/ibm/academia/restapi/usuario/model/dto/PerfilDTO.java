package com.ibm.academia.restapi.usuario.model.dto;

import java.util.Set;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.restapi.usuario.model.entities.Tarjeta;
import com.ibm.academia.restapi.usuario.model.enums.Pasion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

	
	private Integer id;
	
	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacio")
	private Pasion pasion;
	
	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacio")
	@Min(value = 7000, message = "El salario minimo es de $7,000")
	private Double salarioMinimo;
	
	private Double salarioMaximo;
	
	@NotNull(message = "Este campo no puede ser nulo")
	@NotEmpty(message = "Este campo no puede ser vacio")
	@Min(value = 18, message = "La edad minima es 18")
	@Max(value = 75, message = "La edad maxima es 75")
	@Positive(message = "Este campo debe ser mayor a 0")
	private Integer edadMinima;
	
	private Integer edadMaxima;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "perfiles"})
	private Set<Tarjeta> tarjetas;
	
	private Integer puerto;
	
}
