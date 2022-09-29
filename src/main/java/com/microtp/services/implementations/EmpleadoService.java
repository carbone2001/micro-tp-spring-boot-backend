package com.microtp.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microtp.dtos.empleado.CreateEmpleadoDTO;
import com.microtp.dtos.empleado.UpdateEmpleadoDTO;
import com.microtp.entities.Empleado;
import com.microtp.entities.Rol;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.repositories.IEmpleadoRepository;
import com.microtp.services.IEmpleadoService;
import com.microtp.services.IRolService;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IEmpleadoRepository empleadoRespository;

	@Autowired
	private CrudUtilsService crudUtilsSvc;

	@Autowired
	private IRolService rolSvc;

	public Empleado save(CreateEmpleadoDTO createEmpleadoDTO) throws RequiredFieldException, EntityNotFoundException {
		Empleado empleado = new Empleado();
		empleado = (Empleado) this.crudUtilsSvc.loadObjectWithDTOValues(createEmpleadoDTO, empleado);

		empleado.setClaveUsuario(this.passwordEncoder.encode(createEmpleadoDTO.getClaveUsuario()));

		List<Integer> rolesIDs = createEmpleadoDTO.getRolesId();
		List<Rol> roles = this.rolSvc.findRolesByIds(rolesIDs);

		empleado.setRoles(roles);

		return this.empleadoRespository.save(empleado);
	}

	public void update(int id, UpdateEmpleadoDTO updateDTO) throws EntityNotFoundException, RequiredFieldException {
		Optional<Empleado> empleadoOpt = this.empleadoRespository.findById(id);

		if (empleadoOpt.isEmpty())
			throw new EntityNotFoundException("No se encontró un empleado de id: " + id);

		Empleado empleado = empleadoOpt.get();
		
		//Cambiar nombre
		if (updateDTO.getNombre() != null && updateDTO.getNombre().isPresent()
				&& !updateDTO.getNombre().get().isBlank()) {
			empleado.setNombre(updateDTO.getNombre().get());
		}

		//Cambiar apellido
		if (updateDTO.getApellido() != null && updateDTO.getApellido().isPresent()
				&& !updateDTO.getApellido().get().isBlank()) {
			empleado.setApellido(updateDTO.getApellido().get());
		}
	
		//Cambiar Roles ID
		if (updateDTO.getRolesId() != null && updateDTO.getRolesId().isPresent()) {
			List<Integer> rolesIDs = updateDTO.getRolesId().get();
			List<Rol> roles = this.rolSvc.findRolesByIds(rolesIDs);
			empleado.setRoles(roles);
		}

		this.empleadoRespository.save(empleado);
	}

	public Optional<Empleado> findById(int id) {
		return this.empleadoRespository.findById(id);
	}

	public List<Empleado> findAll() {
		return this.empleadoRespository.findAll();
	}

	public void delete(int id) {
		this.empleadoRespository.deleteById(id);
	}

	@Override
	public Empleado findByNombreUsuario(String usuario) {
		return this.empleadoRespository.findFirstByNombreUsuario(usuario);
	}

	@Override
	public void deleteByNombreUsuario(String usuario) {
		this.empleadoRespository.deleteAllByNombreUsuario(usuario);
	}

	@Override
	public void deleteAll() {
		this.empleadoRespository.deleteAll();
	}

}
