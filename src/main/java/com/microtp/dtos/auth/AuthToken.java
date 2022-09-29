package com.microtp.dtos.auth;

import java.util.List;

import com.microtp.entities.Rol;

import lombok.Data;

@Data
public class AuthToken {
	private String username;
	private List<Rol> roles;
}
