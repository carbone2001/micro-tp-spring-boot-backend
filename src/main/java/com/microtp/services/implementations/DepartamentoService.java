package com.microtp.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microtp.dtos.departamento.CreateDepartamentoDTO;
import com.microtp.dtos.departamento.UpdateDepartamentoDTO;
import com.microtp.entities.Departamento;
import com.microtp.entities.Localizacion;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.repositories.IDepartamentoRepository;
import com.microtp.services.IDepartamentoService;
import com.microtp.services.ILocalizacionService;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private IDepartamentoRepository departamentoRepository;

	@Autowired
	private ILocalizacionService localizacionSvc;

	@Autowired
	private CrudUtilsService crudUtilsSvc;

	@Override
	public Departamento save(CreateDepartamentoDTO saveDTO) throws RequiredFieldException, EntityNotFoundException {
		Departamento departamento = new Departamento();
		departamento = (Departamento) this.crudUtilsSvc.loadObjectWithDTOValues(saveDTO, departamento);

		// Obtener localizacion
		Optional<Localizacion> localizacionOptional = this.localizacionSvc.findById(saveDTO.getLocalizacionId());
		if (localizacionOptional.isPresent())
			departamento.setLocalizacion(localizacionOptional.get());

		return departamentoRepository.save(departamento);
	}

	@Override
	public void update(int id, UpdateDepartamentoDTO updateDTO) throws EntityNotFoundException {
		Optional<Departamento> departamentoOptional = this.departamentoRepository.findById(id);

		if (departamentoOptional.isEmpty()) {
			throw new EntityNotFoundException("No existe un departamento con id " + id);
		}

		Departamento departamento = departamentoOptional.get();

		if (updateDTO.getDescripcion() != null && updateDTO.getDescripcion().isPresent())
			departamento.setDescripcion(updateDTO.getDescripcion().get());
		
		if (updateDTO.getLocalizacionId() != null && updateDTO.getLocalizacionId().isPresent()) {
			Optional<Localizacion> localizacionOptional = this.localizacionSvc.findById(updateDTO.getLocalizacionId().get());
			if (localizacionOptional.isPresent())
				departamento.setLocalizacion(localizacionOptional.get());
		}
		
		this.departamentoRepository.save(departamento);
	}

	@Override
	public Optional<Departamento> findById(int id) {
		return this.departamentoRepository.findById(id);
	}

	@Override
	public List<Departamento> findAll() {
		return this.departamentoRepository.findAll();
	}

	@Override
	public void delete(int id) {
		this.departamentoRepository.deleteById(id);
	}

}
