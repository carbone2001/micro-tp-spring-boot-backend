package com.microtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microtp.dtos.microtp.Punto10MicroTPView;
import com.microtp.dtos.microtp.Punto14MicroTPView;
import com.microtp.dtos.microtp.Punto4MicroTPView;
import com.microtp.dtos.microtp.Punto7MicroTPView;
import com.microtp.entities.Departamento;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Integer> {
	@Query("from Departamento d left JOIN FETCH d.localizacion")
	List<Departamento> findAll();
	
	@Query(value = "SELECT d.descripcion AS departamento, SUM(e.salario) AS salarios_totales \r\n"
			+ "FROM empleados e JOIN departamentos d USING (departamento_id) \r\n"
			+ "GROUP BY d.descripcion \r\n"
			+ "ORDER BY salarios_totales DESC", nativeQuery = true)
	List<Punto4MicroTPView> punto4MicroTP();
	
	@Query(value = "SELECT departamento_id, AVG(empleados.salario) as SALARIOS\r\n"
			+ "FROM departamentos NATURAL JOIN empleados \r\n"
			+ "GROUP BY departamento_id", nativeQuery = true)
	List<Punto7MicroTPView> punto7MicroTP();
	
	@Query(value="SELECT descripcion, SUM(salario) total_salarios \r\n"
			+ "FROM (\r\n"
			+ "    SELECT departamento_id, \r\n"
			+ "    d.descripcion, \r\n"
			+ "    e.salario salario \r\n"
			+ "    FROM departamentos d NATURAL JOIN empleados e)\r\n"
			+ "GROUP BY descripcion HAVING SUM(salario) > :salarioMinimo", nativeQuery= true)
	List<Punto10MicroTPView> punto10MicroTP(@Param("salarioMinimo") Double salarioMinimo);
	
	@Query(value = "SELECT departamento_id, descripcion, SUM(salario) total_salarios\r\n"
			+ "FROM (\r\n"
			+ "    SELECT departamento_id, descripcion, salario  \r\n"
			+ "    FROM departamentos d NATURAL JOIN empleados e) \r\n"
			+ "GROUP BY departamento_id, descripcion \r\n"
			+ "HAVING SUM(salario) >  (SELECT AVG(salario) FROM empleados)", nativeQuery = true)
	List<Punto14MicroTPView> punto14MicroTP();
}
