package com.microtp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microtp.dtos.microtp.Punto10MicroTPView;
import com.microtp.dtos.microtp.Punto14MicroTPView;
import com.microtp.dtos.microtp.Punto3MicroTPView;
import com.microtp.dtos.microtp.Punto4MicroTPView;
import com.microtp.dtos.microtp.Punto5MicroTPView;
import com.microtp.dtos.microtp.Punto7MicroTPView;
import com.microtp.dtos.microtp.Punto8MicroTPView;
import com.microtp.dtos.microtp.Punto9MicroTPView;
import com.microtp.entities.Empleado;
import com.microtp.repositories.IEmpleadoRepository;
import com.microtp.services.IMicroTPService;

/*@SelectClasses({
	RolControllerTest.class,
	DepartamentoControllerTest.class,
})*/
@SpringBootTest
@Suite
//@SelectPackages("com.microtp.service")
//@SelectClasses({ AuthIntegrationTest.class })
public class MicroTpApplicationTests {
	@Autowired
	private IMicroTPService microTPSvc;
	
	@Autowired
	private IEmpleadoRepository empleadoRepo;
	
	@Test
	@Disabled
	@DisplayName("Carga de entidades - Micro TP")
	public void loadMockEntities() throws Exception {
		this.microTPSvc.loadMockEntities();
	}
	
	@Test
	@Disabled("Carga de entidades (DESHABILITADO) - Micro TP")
	//@DisplayName("Test Store procedure - Micro TP")
	public void testStoredProcedure() {
		this.empleadoRepo.testStoredProcedure();
		//List<Empleado> result = null;//this.empleadoRepo.testStoredProcedure();
		//int result = 9;//this.empleadoRepo.testStoredProcedure(4, 5);
		//assertTrue(result != null);
	}
	
	@Test
	@DisplayName("Punto 1 - Micro TP")
	public void punto1MicroTPTest() {
		List<Empleado> empleados = this.microTPSvc.punto1MicroTP();
		assertTrue(empleados.size() == 6);
	}

	@Test
	@DisplayName("Punto 2 - Micro TP")
	public void punto2MicroTPTest() {
		List<Empleado> empleados = this.microTPSvc.punto2MicroTP();
		LocalDate now = LocalDate.now().minus(3, ChronoUnit.MONTHS);
		Boolean failedTest = false;
		for(Empleado empleado: empleados) {
			if(empleado.getFechaContratacion().compareTo(now) > 0) {
				failedTest = true;
				break;
			}
		}
		assertTrue(!failedTest);
	}
	
	@Test
	@DisplayName("Punto 3 - Micro TP")
	public void punto3MicroTPTest() {
		List<Punto3MicroTPView> result = this.microTPSvc.punto3MicroTP();
		assertTrue(result != null && result.size() > 0);
	}

	@Test
	@DisplayName("Punto 4 - Micro TP")
	public void punto4MicroTPTest() {
		List<Punto4MicroTPView> result = this.microTPSvc.punto4MicroTP();
		assertTrue(result != null && result.size() > 0);
	}
	
	@Test
	@DisplayName("Punto 5 - Micro TP")
	public void punto5MicroTPTest() {
		List<Punto5MicroTPView> result = this.microTPSvc.punto5MicroTP();
		assert(result != null && result.size() > 0);
	}
	
	@Test
	@DisplayName("Punto 6 - Micro TP")
	public void punto6MicroTPTest() {
		Double result = this.microTPSvc.punto6MicroTP();
		assertTrue(result.intValue() == 6457);
	}
	
	@Test
	@DisplayName("Punto 7 - Micro TP")
	public void punto7MicroTPTest() {
		List<Punto7MicroTPView> result = this.microTPSvc.punto7MicroTP();
		assertTrue(result != null && result.size() > 0);
	}
	
	@Test
	@DisplayName("Punto 8 - Micro TP")
	public void punto8MicroTPTest() {
		List<Punto8MicroTPView> result = this.microTPSvc.punto8MicroTP();
		assertTrue(result != null && result.size() == 2);
	}
	
	@Test
	@DisplayName("Punto 9 - Micro TP")
	public void punto9MicroTPTest() {
		List<Punto9MicroTPView> result = this.microTPSvc.punto9MicroTP();
		assertTrue(result != null && result.size() == 4);
	}
	
	@Test
	@DisplayName("Punto 10 - Micro TP")
	public void punto10MicroTPTest() {
		List<Punto10MicroTPView> result = this.microTPSvc.punto10MicroTP(500.00);
		assertTrue(result != null && result.size() == 3);
	}
	
	@Test
	@DisplayName("Punto 11 - Micro TP")
	public void punto11MicroTPTest() {
		List<Empleado> result = this.microTPSvc.punto11MicroTP(487);
		assertTrue(result != null && result.size() == 2);
	}
	
	@Test
	@DisplayName("Punto 12 - Micro TP")
	public void punto12MicroTPTest() {
		List<Empleado> result = this.microTPSvc.punto12MicroTP();
		assertTrue(result != null && result.size() == 6);
	}
	
	@Test
	@DisplayName("Punto 13 - Micro TP")
	public void punto13MicroTPTest() {
		List<Empleado> result = this.microTPSvc.punto13MicroTP(467);
		assertTrue(result != null && result.size() == 4);
	}
	
	@Test
	@DisplayName("Punto 14 - Micro TP")
	public void punto14MicroTPTest() {
		List<Punto14MicroTPView> result = this.microTPSvc.punto14MicroTP();
		assertTrue(result != null && result.size() == 3);
	}
}