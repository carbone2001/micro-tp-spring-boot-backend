package com.microtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microtp.dtos.microtp.Punto3MicroTPView;
import com.microtp.dtos.microtp.Punto5MicroTPView;
import com.microtp.entities.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

	@Query("from Empleado e left join fetch e.roles")
	List<Empleado> findAll();

	Empleado findFirstByNombreUsuario(String nombreUsuario);

	void deleteAllByNombreUsuario(String nombreUsuario);

	// @Query(value="SELECT * FROM empleados e WHERE MONTHS_BETWEEN(SYSDATE,
	// e.fecha_contratacion) >= 3", nativeQuery = true)
	// @Procedure("SYSTEM.SP_TEST")
	// int testStoredProcedure(@Param("in_param_1")int param1,
	// @Param("in_param_2")int param2);
	@Procedure("auto")
	void testStoredProcedure();

	@Query(value = "SELECT * FROM empleados WHERE departamento_id IS NOT NULL", nativeQuery = true)
	List<Empleado> punto1MicroTP();

	@Query(value = "SELECT * FROM empleados e WHERE MONTHS_BETWEEN(SYSDATE, e.fecha_contratacion) >= 3", nativeQuery = true)
	List<Empleado> punto2MicroTP();

	@Query(value = "SELECT e.nombre, e.apellido, d.descripcion AS nombre_departamento FROM empleados e NATURAL JOIN departamentos d", nativeQuery = true)
	List<Punto3MicroTPView> punto3MicroTP();

	@Query(value = "SELECT e.nombre, e.apellido, l.descripcion\r\n" + "FROM empleados e \r\n"
			+ "JOIN departamentos d ON d.departamento_id = e.departamento_id\r\n"
			+ "JOIN localizaciones l ON d.localizacion_id = l.localizacion_id", nativeQuery = true)
	List<Punto5MicroTPView> punto5MicroTP();

	@Query(value = "SELECT SUM(salario*1.7) total_salarios FROM empleados", nativeQuery = true)
	Double punto6MicroTP();
	
	@Query(value = "SELECT * FROM empleados e WHERE e.salario > (SELECT salario FROM empleados WHERE empleado_id = :empleadoId)", nativeQuery = true)
	List<Empleado> punto11MicroTP(@Param("empleadoId") Integer empleadoId);
	
	@Query(value = "SELECT * FROM empleados m \r\n"
			+ " WHERE NOT EXISTS (SELECT * FROM empleados e WHERE e.manager_id = m.empleado_id)", nativeQuery = true)
	List<Empleado> punto12MicroTP();
	
	@Query(value = "SELECT * FROM empleados NATURAL JOIN (SELECT * FROM departamentos d WHERE d.localizacion_id != :localizacionId)", nativeQuery = true)
	List<Empleado> punto13MicroTP(@Param("localizacionId") Integer localizacionId);
}