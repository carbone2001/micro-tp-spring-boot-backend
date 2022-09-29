package com.microtp.services.implementations;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microtp.dtos.departamento.CreateDepartamentoDTO;
import com.microtp.dtos.empleado.CreateEmpleadoDTO;
import com.microtp.dtos.microtp.Punto10MicroTPView;
import com.microtp.dtos.microtp.Punto14MicroTPView;
import com.microtp.dtos.microtp.Punto3MicroTPView;
import com.microtp.dtos.microtp.Punto4MicroTPView;
import com.microtp.dtos.microtp.Punto5MicroTPView;
import com.microtp.dtos.microtp.Punto7MicroTPView;
import com.microtp.dtos.microtp.Punto8MicroTPView;
import com.microtp.dtos.microtp.Punto9MicroTPView;
import com.microtp.entities.Departamento;
import com.microtp.entities.Empleado;
import com.microtp.entities.Localizacion;
import com.microtp.entities.Rol;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.repositories.IDepartamentoRepository;
import com.microtp.repositories.IEmpleadoRepository;
import com.microtp.repositories.ILocalizacionRepository;
import com.microtp.repositories.IRolRepository;
import com.microtp.services.IDepartamentoService;
import com.microtp.services.IEmpleadoService;
import com.microtp.services.IMicroTPService;

@Service
public class MicroTPService implements IMicroTPService {
	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Autowired
	private IEmpleadoService empleadoSvc;

	@Autowired
	private IDepartamentoRepository departamentoRepository;

	@Autowired
	private IDepartamentoService departamentoSvc;

	@Autowired
	private ILocalizacionRepository localizacionRepository;

	@Autowired
	private IRolRepository rolRepository;

	@Override
	public Integer testStoredProcedure(Integer param1, Integer param2) {
		return 0;// this.empleadoRepository.testStoredProcedure(param1, param2);
	}

	@Override
	public void loadMockEntities() throws ParseException, RequiredFieldException, EntityNotFoundException {
		// Limpiar base de datos
		this.empleadoRepository.deleteAll();
		this.rolRepository.deleteAll();
		this.departamentoRepository.deleteAll();
		this.localizacionRepository.deleteAll();

		// Cargar localizaciones
		List<String> localizacionNames = new ArrayList<String>();
		localizacionNames.add("Ciudad Autonoma de Buenos Aires");
		localizacionNames.add("Quilmes");
		localizacionNames.add("Temperley");
		localizacionNames.add("Almirante Brown");

		localizacionNames.forEach((localizacionName) -> {
			Localizacion localizacion = new Localizacion();
			localizacion.setDescripcion(localizacionName);
			this.localizacionRepository.save(localizacion);
		});

		List<Localizacion> localizaciones = this.localizacionRepository.findAll();

		// Cargar departamentos
		List<String> departamentosNames = new ArrayList<String>();
		departamentosNames.add("SISTEMAS");
		departamentosNames.add("FINANZAS");
		departamentosNames.add("OPERACIONES");
		departamentosNames.add("RRHH");

		int index = 0;
		for (String departamentoName : departamentosNames) {
			CreateDepartamentoDTO departamento = new CreateDepartamentoDTO();
			departamento.setDescripcion(departamentoName);
			departamento.setLocalizacionId(localizaciones.get(index).getId());
			this.departamentoSvc.save(departamento);
			index++;
		}

		List<Departamento> departamentos = this.departamentoRepository.findAll();

		// Cargar roles
		List<String> rolNames = new ArrayList<String>();
		rolNames.add("ADMIN");
		rolNames.add("VENDEDOR");
		rolNames.add("GERENTE");

		rolNames.forEach((rolName) -> {
			Rol rol = new Rol();
			rol.setDescripcion(rolName);
			this.rolRepository.save(rol);
		});

		List<Rol> roles = this.rolRepository.findAll();

		// Cargar empleados
		List<Empleado> empleados = new ArrayList<Empleado>();

		for (int i = 0; i < 10; i++) {
			CreateEmpleadoDTO emp = new CreateEmpleadoDTO();
			emp.setNombre("nombre-".concat(String.valueOf(i)));
			emp.setApellido("apellido-".concat(String.valueOf(i)));
			emp.setNombreUsuario("usuario-".concat(String.valueOf(i)));
			emp.setClaveUsuario("Asdasd123123!");
			emp.setSalario((i * 13.00 * 1000));
			emp.setFechaContratacion(LocalDate.of(2022, i + 1, 5 + i));
			emp.setFechaNacimiento(LocalDate.of(1990 + i, i + 1, 2 + i));
			emp.setImagenPerfil("https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg");

			if (i % 2 == 0 && empleados.size() > 0) {
				// SET MANAGER ID
				emp.setManangerId(empleados.get((int) (Math.random() * 100) * empleados.size() / 100).getId());
			} else {
				// SET DEPARTAMENTO
				int randomId = departamentos.get((int) (Math.random() * 100) * departamentos.size() / 100).getId();
				emp.setDepartamentoId(randomId);
			}

			// SET ROLES
			int cantidadRoles = (int) Math.random() * 100 * roles.size() / 100;
			Set<Integer> rolesId = new HashSet<Integer>();
			while (rolesId.size() < cantidadRoles) {
				int randomRolIndex = (int) Math.random() * 100 * roles.size() / 100;
				rolesId.add(roles.get(randomRolIndex).getId());
			}
			emp.setRolesId(new ArrayList<Integer>(rolesId));

			empleados.add(this.empleadoSvc.save(emp));
		}
	}

	@Override
	public List<Empleado> punto1MicroTP() {
		return this.empleadoRepository.punto1MicroTP();
	}

	@Override
	public List<Empleado> punto2MicroTP() {
		return this.empleadoRepository.punto2MicroTP();
	}

	@Override
	public List<Punto3MicroTPView> punto3MicroTP() {
		return this.empleadoRepository.punto3MicroTP();
	}

	@Override
	public List<Punto4MicroTPView> punto4MicroTP() {
		return this.departamentoRepository.punto4MicroTP();
	}

	@Override
	public List<Punto5MicroTPView> punto5MicroTP() {
		return this.empleadoRepository.punto5MicroTP();
	}

	@Override
	public Double punto6MicroTP() {
		return this.empleadoRepository.punto6MicroTP();
	}

	@Override
	public List<Punto7MicroTPView> punto7MicroTP() {
		return this.departamentoRepository.punto7MicroTP();
	}

	@Override
	public List<Punto8MicroTPView> punto8MicroTP() {
		return this.localizacionRepository.punto8MicroTP();
	}

	@Override
	public List<Punto9MicroTPView> punto9MicroTP() {
		return this.localizacionRepository.punto9MicroTP();
	}

	@Override
	public List<Punto10MicroTPView> punto10MicroTP(Double salarioMinimo) {
		return this.departamentoRepository.punto10MicroTP(salarioMinimo);
	}

	@Override
	public List<Empleado> punto11MicroTP(Integer empleadoId) {
		return this.empleadoRepository.punto11MicroTP(empleadoId);
	}

	@Override
	public List<Empleado> punto12MicroTP() {
		return this.empleadoRepository.punto12MicroTP();
	}

	@Override
	public List<Empleado> punto13MicroTP(Integer localizacionId) {
		return this.empleadoRepository.punto13MicroTP(localizacionId);
	}

	@Override
	public List<Punto14MicroTPView> punto14MicroTP() {
		return this.departamentoRepository.punto14MicroTP();
	}

}
