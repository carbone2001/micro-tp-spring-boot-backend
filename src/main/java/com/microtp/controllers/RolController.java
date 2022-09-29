package com.microtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microtp.dtos.api.ApiError;
import com.microtp.dtos.rol.CreateRolDTO;
import com.microtp.entities.Rol;
import com.microtp.entities.api.CommonErrorCode;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.services.IRolService;

@RestController
@RequestMapping("/api/roles/")
public class RolController {
	@Autowired
	private IRolService rolSvc;

	@PostMapping("/")
	public ResponseEntity<?> createRol(@RequestBody CreateRolDTO rolData) {
		try {
			Rol rol = this.rolSvc.save(rolData);
			return ResponseEntity.ok(rol);
		} catch (RequiredFieldException e) {
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), CommonErrorCode.MISSING_FIELDS));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> getRoles(){
		return ResponseEntity.ok(this.rolSvc.findAll());
	}
}
