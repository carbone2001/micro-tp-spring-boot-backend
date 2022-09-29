package com.microtp.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import com.microtp.dtos.auth.UserAuthCredentials;

public interface IAuthService {
	String login(UserAuthCredentials credentials) throws DisabledException,BadCredentialsException;
}
