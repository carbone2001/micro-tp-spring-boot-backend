package com.microtp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microtp.dtos.api.ApiError;
import com.microtp.dtos.empleado.CreateEmpleadoDTO;
import com.microtp.dtos.empleado.EmpleadoDTO;
import com.microtp.dtos.empleado.UpdateEmpleadoDTO;
import com.microtp.entities.Empleado;
import com.microtp.entities.api.CommonErrorCode;
import com.microtp.entities.api.EmpleadoErrorCode;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.services.IEmpleadoService;

@RestController
@RequestMapping("api/empleados/")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoSvc;

	@PostMapping("/")
	public ResponseEntity<?> createEmpleado(@RequestBody CreateEmpleadoDTO empleadoData) {
		try {
			Empleado empleado = this.empleadoSvc.save(empleadoData);
			return ResponseEntity.ok(EmpleadoDTO.fromEmpleado(empleado));
		} catch (RequiredFieldException e) {
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), CommonErrorCode.MISSING_FIELDS));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> findAllEmpleados(){
		try {
			List<Empleado> empleados = this.empleadoSvc.findAll();
			
			//Mediante un map: List<Empleado> => List<EmpleadoDTO>
			List<EmpleadoDTO> empleadosDTO = empleados.stream()
					.map(e -> EmpleadoDTO.fromEmpleado(e))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(empleadosDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpleado(@PathVariable("id") Integer id, @RequestBody UpdateEmpleadoDTO updateDTO){
		try {
			this.empleadoSvc.update(id, updateDTO);
			return ResponseEntity.ok(null);
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), EmpleadoErrorCode.EMPLEADO_NF));
		}
		catch(Exception e) {
			return ResponseEntity.internalServerError().body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}
}
