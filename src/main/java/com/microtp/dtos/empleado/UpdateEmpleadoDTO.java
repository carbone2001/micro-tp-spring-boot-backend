package com.microtp.dtos.empleado;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class UpdateEmpleadoDTO {
	Optional<String> nombre;

	Optional<String> apellido;

	Optional<String> nombreUsuario;

	Optional<String> claveUsuario;

	Optional<Integer> fechaNacimiento;

	Optional<String> imagenPerfil;

	Optional<Date> fechaContratacion;

	Optional<Double> salario;

	Optional<Integer> departamentoId;

	Optional<Integer> manangerId;

	Optional<List<Integer>> rolesId;
}
