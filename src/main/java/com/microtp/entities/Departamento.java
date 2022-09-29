package com.microtp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="departamentos")
@JsonIgnoreProperties(value = { "localizacion" })
public class Departamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="departamento_id")
	private Integer id;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@Column(name = "localizacion_id", insertable = false, updatable = false)
	private Integer localizacionId;
	
	@ManyToOne
	@JoinColumn(name="localizacion_id")
	private Localizacion localizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public Integer getLocalizacionId() {
		return localizacionId;
	}

	public void setLocalizacionId(Integer localizacionId) {
		this.localizacionId = localizacionId;
	}
}
