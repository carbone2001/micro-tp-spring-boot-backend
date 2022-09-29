package com.microtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microtp.dtos.api.ApiError;
import com.microtp.dtos.auth.JwtResponse;
import com.microtp.dtos.auth.UserAuthCredentials;
import com.microtp.entities.api.AuthErrorCode;
import com.microtp.entities.api.CommonErrorCode;
import com.microtp.services.IAuthService;

@RestController
public class AuthController {

	@Autowired
	private IAuthService authSvc;

	@RequestMapping(value = "/api/auth", method = RequestMethod.POST)
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody UserAuthCredentials authenticationRequest)
			throws Exception {
		try {
			final String token = this.authSvc.login(authenticationRequest);
			
			// Devolver respuesta con token
			return ResponseEntity.ok(new JwtResponse(token));
			
		} catch (DisabledException e) {
			return ResponseEntity.badRequest().body(new ApiError(AuthErrorCode.USER_DISABLED, e.getMessage()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body(new ApiError(AuthErrorCode.INVALID_CRENDENTIALS, e.getMessage()));
		} catch(Exception e) {
			return ResponseEntity.internalServerError().body(new ApiError(CommonErrorCode.UNKNOWN_ERROR, e.getMessage()));
		}
	}
}
