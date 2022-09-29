package com.microtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microtp.dtos.microtp.Punto8MicroTPView;
import com.microtp.dtos.microtp.Punto9MicroTPView;
import com.microtp.entities.Localizacion;

@Repository
public interface ILocalizacionRepository extends JpaRepository<Localizacion, Integer> {
	@Query("from Localizacion l left JOIN FETCH l.departamentos")
	List<Localizacion> findAll();
	
	@Query(value="WITH \r\n"
			+ "    empleados_departamentos AS (\r\n"
			+ "        SELECT departamento_id, salario, localizacion_id \r\n"
			+ "        FROM empleados  NATURAL JOIN departamentos),\r\n"
			+ "    localizaciones_salarios AS (\r\n"
			+ "        SELECT localizacion_id, descripcion, SUM(salario) as salarios\r\n"
			+ "        FROM empleados_departamentos\r\n"
			+ "        NATURAL JOIN localizaciones GROUP BY localizacion_id,descripcion),\r\n"
			+ "    max_sueldo AS (\r\n"
			+ "        SELECT MAX(salarios) as salarios\r\n"
			+ "        FROM localizaciones_salarios),\r\n"
			+ "    min_sueldo AS (\r\n"
			+ "        SELECT MIN(salarios) as salarios\r\n"
			+ "        FROM localizaciones_salarios)\r\n"
			+ "   \r\n"
			+ "SELECT l.descripcion, l.salarios\r\n"
			+ "   FROM localizaciones_salarios l, max_sueldo, min_sueldo\r\n"
			+ "   WHERE l.salarios = max_sueldo.salarios OR  l.salarios = min_sueldo.salarios", nativeQuery = true)
	List<Punto8MicroTPView> punto8MicroTP();
	
	@Query(value="SELECT l.descripcion localizacion, total_deps.departamentos \r\n"
			+ "FROM (\r\n"
			+ "    SELECT localizacion_id, COUNT(*) as departamentos \r\n"
			+ "    FROM departamentos d GROUP BY localizacion_id) total_deps \r\n"
			+ "NATURAL JOIN localizaciones l", nativeQuery = true)
	List<Punto9MicroTPView> punto9MicroTP();
}
