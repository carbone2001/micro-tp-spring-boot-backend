package com.microtp.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "localizaciones")
public class Localizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "localizacion_id")
	private Integer id;

	@Column(name = "descripcion", length = 40, nullable = false)
	private String descripcion;

	@OneToMany(mappedBy = "localizacion")
	private Set<Departamento> departamentos;

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

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
}
