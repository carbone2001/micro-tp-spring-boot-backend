package com.microtp.dtos.departamento;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateDepartamentoDTO {
	private Optional<String> descripcion;
	
	private Optional<Integer> localizacionId;
}
