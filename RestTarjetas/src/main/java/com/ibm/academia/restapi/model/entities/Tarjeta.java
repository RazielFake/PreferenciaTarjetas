package com.ibm.academia.restapi.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.restapi.model.enums.NombreTarjeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tarjetas", schema = "preferencia")
public class Tarjeta implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private NombreTarjeta nombreTarjeta;
	
	@ToString.Exclude
	@ManyToMany(mappedBy = "tarjetas", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "tarjetas"})
	private Set<Perfil> perfiles; 
	
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	
	public Tarjeta(NombreTarjeta nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombreTarjeta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(id, other.id) && nombreTarjeta == other.nombreTarjeta;
	}

	@PrePersist
	private void antesPersisitir() {
		this.fechaAlta = new Date();
	}
	
	@PreUpdate
	private void antesActualizar() {
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 4339079030187244643L;
	
}
