package com.microtp.dtos.departamento;

import com.microtp.annotations.RequiredField;

import lombok.Data;

@Data
public class CreateDepartamentoDTO {
	
	@RequiredField
	private String descripcion;
	
	@RequiredField
	private Integer localizacionId;
}
