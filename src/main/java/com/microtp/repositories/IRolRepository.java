package com.microtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microtp.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
	@Query("select r from Rol r where r.id in :roleIDs")
	List<Rol> findByIds(@Param("roleIDs") List<Integer> rolIds);
}