package com.microtp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microtp.dtos.api.ApiError;
import com.microtp.dtos.departamento.CreateDepartamentoDTO;
import com.microtp.dtos.departamento.UpdateDepartamentoDTO;
import com.microtp.entities.Departamento;
import com.microtp.entities.api.CommonErrorCode;
import com.microtp.entities.api.DepartamentoErrorCode;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.services.IDepartamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("api/departamentos/")
public class DepartamentoController {

	@Autowired
	private IDepartamentoService departamentoSvc;

	@Operation(summary = "Para crear un departamento", description = "En este endpoint se le pasan los parametros correspondientes para crear un departamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario registrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class)) }),
			@ApiResponse(responseCode = "400", description = "Parámetros invalidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)) }), })
	@PostMapping("/")
	public ResponseEntity<?> createDepartamento(@RequestBody CreateDepartamentoDTO departamentoData) {
		Departamento departamento = null;

		try {
			departamento = this.departamentoSvc.save(departamentoData);
		} catch (RequiredFieldException e) {
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), CommonErrorCode.MISSING_FIELDS));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(new ApiError(e.getMessage(), DepartamentoErrorCode.DEPARTAMENTO_NF));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

		return ResponseEntity.ok().body(departamento);
	}

	@GetMapping("/")
	public ResponseEntity<Object> getDepartamentos() {
		List<Departamento> departamentos = new ArrayList<Departamento>();

		try {
			departamentos = this.departamentoSvc.findAll();
		} catch (Exception e) {
			log.severe(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

		return ResponseEntity.ok(departamentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("id") Integer id) {
		Optional<Departamento> departamentoOptional;
		Departamento departamento = null;

		try {
			departamentoOptional = this.departamentoSvc.findById(id);
			departamento = departamentoOptional.get();
		} catch (EntityNotFoundException e) {
			log.severe(e.getMessage());
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		} catch (Exception e) {
			log.severe(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

		return ResponseEntity.ok(departamento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDepartamentoById(@PathVariable("id") Integer id,
			@RequestBody UpdateDepartamentoDTO updateData) {
		try {
			this.departamentoSvc.update(id, updateData);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(new ApiError(e.getMessage(), DepartamentoErrorCode.DEPARTAMENTO_NF));
		} catch (Exception e) {
			log.severe(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

		return ResponseEntity.ok(null);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartamentoById(@PathVariable("id") Integer id) {
		try {
			this.departamentoSvc.delete(id);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(new ApiError(e.getMessage(), DepartamentoErrorCode.DEPARTAMENTO_NF));
		} catch (Exception e) {
			log.severe(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

		return ResponseEntity.ok(null);
	}
}
