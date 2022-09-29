package com.microtp.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empleado_id")
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;

	@Column(name = "nombre_usuario", nullable = false, length = 50)
	private String nombreUsuario;

	@Column(name = "clave_usuario", nullable = false, length = 72)
	private String claveUsuario;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "imagen_perfil", length = 100)
	private String imagenPerfil;

	@Column(name = "fecha_contratacion", nullable = false)
	private LocalDate fechaContratacion;

	@Column(name = "salario", nullable = false)
	private Double salario;

	@Column(name = "departamento_id")
	private Integer departamentoId;

	@Column(name = "manager_id")
	private Integer manangerId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rol_usuarios", joinColumns = @JoinColumn(name = "empleado_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Rol> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}

	public Integer getManangerId() {
		return manangerId;
	}

	public void setManangerId(Integer manangerId) {
		this.manangerId = manangerId;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	/*public EmpleadoDTO toEmpleadoDTO() {
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		
		//Carga el valor de las propiedades de la instancia actual al DTO
		BeanUtils.copyProperties(this, empleadoDTO);
		return empleadoDTO;
	}*/

}
