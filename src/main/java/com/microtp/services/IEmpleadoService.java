package com.microtp.services;

import com.microtp.dtos.empleado.CreateEmpleadoDTO;
import com.microtp.dtos.empleado.UpdateEmpleadoDTO;
import com.microtp.entities.Empleado;

public interface IEmpleadoService extends IBasicCrudService<Empleado, CreateEmpleadoDTO, UpdateEmpleadoDTO> {
	
	//Empleado regist(CreateEmpleadoDTO saveDTO) throws RequiredFieldException, EntityNotFoundException;
	
	/*void update(int id, UpdateEmpleadoDTO updateDTO) throws EntityNotFoundException;
	
	Optional<Empleado> findById(int id) throws EntityNotFoundException;
	
	List<Empleado> findAll();
	
	void delete(int id) throws EntityNotFoundException;*/
	
	Empleado findByNombreUsuario(String usuario);
	
	void deleteByNombreUsuario(String usuario);
	
	void deleteAll();
}
