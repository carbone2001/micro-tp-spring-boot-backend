package com.microtp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.microtp.dtos.empleado.CreateEmpleadoDTO;
import com.microtp.dtos.empleado.EmpleadoDTO;
import com.microtp.entities.Empleado;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.services.IEmpleadoService;

@SpringBootTest
public class EmpleadoControllerTest {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IEmpleadoService empleadoSvc;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	void createEmpleado() throws RequiredFieldException, EntityNotFoundException {
		CreateEmpleadoDTO empleadoDTO = new CreateEmpleadoDTO();
		empleadoDTO.setNombre("Lucas");
		empleadoDTO.setApellido("Carbone");
		empleadoDTO.setNombreUsuario("admin");
		empleadoDTO.setClaveUsuario("Asdasd123123!");
		empleadoDTO.setFechaContratacion(LocalDate.of(2022, 8, 31));
		empleadoDTO.setFechaNacimiento(LocalDate.of(2001, 1, 10));
		empleadoDTO.setSalario(1500D);
		empleadoDTO.setDepartamentoId(146);
		empleadoDTO.setImagenPerfil("https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg");
		List<Integer> roles = new ArrayList<Integer>();
		roles.add(127);
		empleadoDTO.setRolesId(roles);

		Empleado empleado = this.empleadoSvc.save(empleadoDTO);

		assertTrue(this.passwordEncoder.matches(empleadoDTO.getClaveUsuario(), empleado.getClaveUsuario()));
	}
}
