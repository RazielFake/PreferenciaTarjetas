package com.ibm.academia.restapi.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.restapi.model.enums.Pasion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfiles", schema = "preferencia")
public class Perfil implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "pasion")
	private Pasion pasion;
	
	@Column(name = "salario_minimo")
	private Double salarioMinimo;
	
	@Column(name = "salario_maximo")
	private Double salarioMaximo;
	
	@Column(name = "edad_minima")
	private Integer edadMinima;
	
	@Column(name = "edad_maxima")
	private Integer edadMaxima;
	
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "perfil_tarjeta", schema = "preferencia",
			joinColumns = @JoinColumn(name = "perfil_id"),
			inverseJoinColumns = @JoinColumn(name = "tarjeta_id")
	)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "perfiles"})
	private Set<Tarjeta> tarjetas;
	
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	
	@Transient
	private Integer puerto;
	
	public Perfil(Pasion pasion, Double salarioMinimo, Double salarioMaximo, Integer edadMinima, Integer edadMaxima) {
		this.pasion = pasion;
		this.salarioMinimo = salarioMinimo;
		this.salarioMaximo = salarioMaximo;
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
	}
	
	public Perfil(Pasion pasion, Double salarioMinimo, Integer edadMinima, Integer edadMaxima) {
		this.pasion = pasion;
		this.salarioMinimo = salarioMinimo;
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edadMaxima, edadMinima, id, pasion, salarioMaximo, salarioMinimo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(edadMaxima, other.edadMaxima) && Objects.equals(edadMinima, other.edadMinima)
				&& Objects.equals(id, other.id) && pasion == other.pasion
				&& Objects.equals(salarioMaximo, other.salarioMaximo)
				&& Objects.equals(salarioMinimo, other.salarioMinimo);
	}

	@PrePersist
	private void antesPersisitir() {
		this.fechaAlta = new Date();
	}
	
	@PreUpdate
	private void antesActualizar() {
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 2397381559648933953L;
	
}
