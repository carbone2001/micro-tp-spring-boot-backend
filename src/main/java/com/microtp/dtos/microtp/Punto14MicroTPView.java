package com.microtp.dtos.microtp;

import org.springframework.beans.factory.annotation.Value;

public interface Punto14MicroTPView {
	Integer getDepartamentoId();
	
	String getDescripcion();
	
	@Value("#{target.total_salarios}")
	Double getTotalSalarios();
}
