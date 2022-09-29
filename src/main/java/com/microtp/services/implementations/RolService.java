package com.microtp.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microtp.dtos.rol.CreateRolDTO;
import com.microtp.dtos.rol.UpdateRolDTO;
import com.microtp.entities.Rol;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.repositories.IRolRepository;
import com.microtp.services.IRolService;

@Service
public class RolService implements IRolService {

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private CrudUtilsService crudUtilsService;

	@Override
	public Rol save(CreateRolDTO saveDTO) throws RequiredFieldException {
		Rol rol = new Rol();
		rol = (Rol) this.crudUtilsService.loadObjectWithDTOValues(saveDTO, rol);
		return this.rolRepository.save(rol);
	}

	@Override
	public void update(int id, UpdateRolDTO updateDTO) throws EntityNotFoundException, Exception {
		Optional<Rol> rolOpt = this.rolRepository.findById(id);

		if (rolOpt.isEmpty())
			throw new EntityNotFoundException("No existe el rol con el id " + id);

		if (updateDTO == null || updateDTO.getDescripcion() == null || updateDTO.getDescripcion().isBlank())
			throw new Exception("Los parámetros enviados son inválidos");

		Rol rol = rolOpt.get();

		if (updateDTO.getDescripcion() != null && !updateDTO.getDescripcion().isBlank()) {
			rol.setDescripcion(updateDTO.getDescripcion());
		}
		
		this.rolRepository.save(rol);
	}

	@Override
	public Optional<Rol> findById(int id) {
		return this.rolRepository.findById(id);
	}

	@Override
	public List<Rol> findAll() {
		return this.rolRepository.findAll();
	}

	@Override
	public void delete(int id) {
		this.rolRepository.deleteById(id);
	}

	@Override
	public List<Rol> findRolesByIds(List<Integer> rolIds) {
		return this.rolRepository.findByIds(rolIds);
	}

}
