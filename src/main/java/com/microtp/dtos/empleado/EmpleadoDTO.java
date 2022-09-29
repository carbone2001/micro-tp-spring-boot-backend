package com.microtp.dtos.empleado;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.microtp.entities.Empleado;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpleadoDTO {
	private Integer id;
	private String nombre;
	private String apellido;
	private String nombreUsuario;
	private Date fechaNacimiento;
	private String imagenPerfil;
	private LocalDate fechaContratacion;
	private Double salario;
	private Integer departamentoId;
	private Integer manangerId;
	
	public static EmpleadoDTO fromEmpleado(Empleado emp) {
		if(emp == null) return null;
		EmpleadoDTO empDTO = new EmpleadoDTO();
		
		//Carga el valor de las propiedades de una instancia a la otra
		BeanUtils.copyProperties(emp, empDTO);
		
		return empDTO;
	}
}
