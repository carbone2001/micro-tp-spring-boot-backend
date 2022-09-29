package com.microtp.services;

import java.util.List;
import java.util.Optional;

import com.microtp.exceptions.EntityNotFoundException;
import com.microtp.exceptions.RequiredFieldException;

public interface IBasicCrudService<T,S, U> {
	T save(S saveDTO) throws RequiredFieldException, EntityNotFoundException;
	
	void update(int id, U updateDTO) throws EntityNotFoundException, Exception;
	
	Optional<T> findById(int id) throws EntityNotFoundException;
	
	List<T> findAll();
	
	void delete(int id) throws EntityNotFoundException;
}
