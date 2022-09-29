package com.microtp.controllers;

import javax.servlet.http.HttpServletResponse;

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
import com.microtp.dtos.localizacion.CreateLocalizacionDTO;
import com.microtp.dtos.localizacion.UpdateLocalizacionDTO;
import com.microtp.entities.Localizacion;
import com.microtp.entities.api.CommonErrorCode;
import com.microtp.entities.api.LocalizacionErrorCode;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.services.ILocalizacionService;

@RestController
@RequestMapping("api/localizaciones/")
public class LocalizacionController {

	@Autowired
	private ILocalizacionService localizacionSvc;

	@PostMapping("/")
	public ResponseEntity<?> createLocalizacion(@RequestBody CreateLocalizacionDTO localizacionData,
			HttpServletResponse responseStatus) {
		try {
			Localizacion localizacion = this.localizacionSvc.save(localizacionData);
			return ResponseEntity.ok(localizacion);
		} catch (RequiredFieldException e) {
			return ResponseEntity.badRequest().body(new ApiError(e.getMessage(), CommonErrorCode.MISSING_FIELDS));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getLocalizaciones() {
		try {
			return ResponseEntity.ok(this.localizacionSvc.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateLocalizacion(@PathVariable("id") Integer id,
			@RequestBody UpdateLocalizacionDTO updateDTO) {
		try {
			this.localizacionSvc.update(id, updateDTO);
			return ResponseEntity.ok(null);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(new ApiError(e.getMessage(), LocalizacionErrorCode.LOCALIZACION_NF));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiError(e.getMessage(), CommonErrorCode.UNKNOWN_ERROR));
		}

	}

}
