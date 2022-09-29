package com.microtp.dtos.microtp;

import org.springframework.beans.factory.annotation.Value;

public interface Punto4MicroTPView {
	String getDepartamento();
	
	@Value("#{target.salarios_totales}")
	Double getSalariosTotales();
}
