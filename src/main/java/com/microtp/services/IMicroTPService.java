package com.microtp.services;

import java.util.List;

import com.microtp.dtos.microtp.Punto10MicroTPView;
import com.microtp.dtos.microtp.Punto14MicroTPView;
import com.microtp.dtos.microtp.Punto3MicroTPView;
import com.microtp.dtos.microtp.Punto4MicroTPView;
import com.microtp.dtos.microtp.Punto5MicroTPView;
import com.microtp.dtos.microtp.Punto7MicroTPView;
import com.microtp.dtos.microtp.Punto8MicroTPView;
import com.microtp.dtos.microtp.Punto9MicroTPView;
import com.microtp.entities.Empleado;

public interface IMicroTPService {

	Integer testStoredProcedure(Integer param1, Integer param2);
	
	void loadMockEntities() throws Exception;

	/**
	 * Empleados que tengan departamento asignado.
	 * 
	 * @return
	 */
	List<Empleado> punto1MicroTP();

	/**
	 * Empleados con fecha de contratación mayor a 3 meses.
	 * 
	 * @return
	 */
	List<Empleado> punto2MicroTP();

	/**
	 * Mostrar listar empleado por nombre y apellido junto con el nombre del
	 * departamento
	 * 
	 * @return
	 */
	List<Punto3MicroTPView> punto3MicroTP();

	/**
	 * Mostrar departamentos con la mayor suma de salarios
	 * 
	 * @return
	 */
	List<Punto4MicroTPView> punto4MicroTP();

	/**
	 * Listar empleados con nombre y apellido junto con la localización de su
	 * departamento
	 * 
	 * @return
	 */
	List<Punto5MicroTPView> punto5MicroTP();

	/**
	 * Mostrar total de salarios a pagar si se aumentarán todos los salarios un 70%
	 * 
	 * @return
	 */
	Double punto6MicroTP();
	
	/**
	 * Salarios promedio de cada departamento
	 * @return
	 */
	List<Punto7MicroTPView> punto7MicroTP();
	
	/**
	 * Localización con mayor salario y localización con menor salario
	 * @return
	 */
	List<Punto8MicroTPView> punto8MicroTP();
	
	/**
	 * Mostrar el total de cada departamento, agrupados por zona.
	 * @return
	 */
	List<Punto9MicroTPView> punto9MicroTP();
	
	/**
	 * Restringir los departamentos que no tengan un salario total mayor a x número.
	 * @param salario
	 * @return
	 */
	List<Punto10MicroTPView> punto10MicroTP(Double salarioMinimo);
	
	/**
	 * Cuántos empleados ganan más que un empleado x
	 * @return
	 */
	List<Empleado> punto11MicroTP(Integer empleadoId);
	
	/**
	 * Traer todos los empleados que NO tengan subordinados
	 * @return
	 */
	List<Empleado> punto12MicroTP();
	
	/**
	 * Buscar todos los empleados de los departamentos que no pertenezcan a la localización x
	 * @return
	 */
	List<Empleado> punto13MicroTP(Integer localizacionId);
	
	/**
	 * Obtener todos los departamentos donde el total de salarios de cada uno sea superior al promedio de salarios de todos los departamentos juntos.
	 * @return
	 */
	List<Punto14MicroTPView> punto14MicroTP();

}
