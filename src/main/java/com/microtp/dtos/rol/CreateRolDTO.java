package com.microtp.dtos.rol;

import com.microtp.annotations.RequiredField;

import lombok.Data;

@Data
public class CreateRolDTO {
	
	@RequiredField
	protected String descripcion;
	
}
