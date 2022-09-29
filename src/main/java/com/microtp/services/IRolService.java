package com.microtp.services;

import java.util.List;

import com.microtp.dtos.rol.CreateRolDTO;
import com.microtp.dtos.rol.UpdateRolDTO;
import com.microtp.entities.Rol;

public interface IRolService extends IBasicCrudService<Rol, CreateRolDTO, UpdateRolDTO> {
	List<Rol> findRolesByIds(List<Integer> rolIds);
}