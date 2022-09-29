package com.microtp.services;

import com.microtp.dtos.localizacion.CreateLocalizacionDTO;
import com.microtp.dtos.localizacion.UpdateLocalizacionDTO;
import com.microtp.entities.Localizacion;

public interface ILocalizacionService extends IBasicCrudService<Localizacion, CreateLocalizacionDTO, UpdateLocalizacionDTO> {

}
