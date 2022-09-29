package com.microtp.dtos.localizacion;

import com.microtp.annotations.RequiredField;

import lombok.Data;

@Data
public class CreateLocalizacionDTO {
	
	@RequiredField
	private String descripcion;
	
}
