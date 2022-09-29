package com.microtp.dtos.microtp;

import org.springframework.beans.factory.annotation.Value;

public interface Punto10MicroTPView {
	String getDescripcion();
	
	@Value("#{target.total_salarios}")
	Double getTotalSalarios();
}
