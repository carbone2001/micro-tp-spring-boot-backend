package com.microtp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.microtp.configurations.jwt.JwtTokenUtil;
import com.microtp.dtos.auth.UserAuthCredentials;
import com.microtp.services.IAuthService;

@Service
public class AuthService implements IAuthService {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public String login(UserAuthCredentials credentials) throws DisabledException,BadCredentialsException {
		//AUTENTICAR CREDENCIALES
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsuario(), 
						credentials.getClave()
						)
				);

		//Obtener usuario
		final UserDetails userDetails = usuarioService
				.loadUserByUsername(credentials.getUsuario());
		
		return this.jwtTokenUtil.generateToken(userDetails);
	}

}
