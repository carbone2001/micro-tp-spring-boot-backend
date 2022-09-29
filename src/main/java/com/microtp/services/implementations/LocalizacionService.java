package com.microtp.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microtp.dtos.localizacion.CreateLocalizacionDTO;
import com.microtp.dtos.localizacion.UpdateLocalizacionDTO;
import com.microtp.entities.Localizacion;
import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;
import com.microtp.repositories.ILocalizacionRepository;
import com.microtp.services.ILocalizacionService;

@Service
public class LocalizacionService implements ILocalizacionService {

	@Autowired
	private ILocalizacionRepository localizacionRepository;

	@Autowired
	private CrudUtilsService crudUtilsSvc;

	@Override
	public Localizacion save(CreateLocalizacionDTO saveDTO) throws RequiredFieldException {
		Localizacion localizacion = new Localizacion();
		localizacion = (Localizacion) this.crudUtilsSvc.loadObjectWithDTOValues(saveDTO, localizacion);
		return this.localizacionRepository.save(localizacion);
	}

	@Override
	public void update(int id, UpdateLocalizacionDTO updateDTO) throws Exception {
		Optional<Localizacion> localizacionOpt = this.localizacionRepository.findById(id);

		if (localizacionOpt.isEmpty())
			throw new EntityNotFoundException("No existe el rol con el id " + id);

		if (updateDTO == null || updateDTO.getDescripcion() == null || updateDTO.getDescripcion().isBlank())
			throw new Exception("Los parámetros enviados son inválidos");

		Localizacion localizacion = localizacionOpt.get();

		if (updateDTO.getDescripcion() != null && !updateDTO.getDescripcion().isBlank()) {
			localizacion.setDescripcion(updateDTO.getDescripcion());
		}

		this.localizacionRepository.save(localizacion);
	}

	@Override
	public Optional<Localizacion> findById(int id) {
		return this.localizacionRepository.findById(id);
	}

	@Override
	public List<Localizacion> findAll() {
		return this.localizacionRepository.findAll();
	}

	@Override
	public void delete(int id) {
		this.localizacionRepository.deleteById(id);
	}

}
