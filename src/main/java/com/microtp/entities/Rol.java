package com.microtp.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="roles")
@JsonIgnoreProperties(value= { "empleados" })
public class Rol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rol_id")
	private Integer id;
	
	@Column(name="descripcion", length=20, nullable=false)
	private String descripcion;
	
	@ManyToMany(mappedBy = "roles")
	Set<Empleado> empleados;

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

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}
}
