package com.microtp.dtos.microtp;

import org.springframework.beans.factory.annotation.Value;

public interface Punto7MicroTPView {
	@Value("#{target.departamento_id}")
	String getDepartamentoId();
	
	Double getSalarios();
}
