package com.microtp.dtos.microtp;

import org.springframework.beans.factory.annotation.Value;

public interface Punto3MicroTPView {
	String getNombre();
	String getApellido();
	
	//Puedo especificar a que nombre de columna está asociado
	@Value("#{target.nombre_departamento}")
	String getNombreDepartamento();
}

