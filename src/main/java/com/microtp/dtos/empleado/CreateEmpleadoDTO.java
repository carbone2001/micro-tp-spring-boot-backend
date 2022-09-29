package com.microtp.dtos.empleado;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microtp.annotations.IgnoreToLoad;
import com.microtp.annotations.RequiredField;

import lombok.Data;

@Data
public class CreateEmpleadoDTO {
	
	@RequiredField
	private String nombre;

	@RequiredField
	private String apellido;

	@RequiredField
	private String nombreUsuario;
	
	@IgnoreToLoad
	@RequiredField
	private String claveUsuario;

	@RequiredField
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaNacimiento;

	@RequiredField
	private String imagenPerfil;

	@RequiredField
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaContratacion;

	@RequiredField
	private Double salario;

	private Integer departamentoId;

	private Integer manangerId;

	@IgnoreToLoad
	@RequiredField
	private List<Integer> rolesId;
}
