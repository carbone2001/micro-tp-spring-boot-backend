package com.microtp.services.implementations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microtp.entities.Empleado;
import com.microtp.entities.Rol;
import com.microtp.repositories.IEmpleadoRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = this.empleadoRepository.findFirstByNombreUsuario(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		for (Rol rol : empleado.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getDescripcion()));
		}

		return new User(empleado.getNombreUsuario(), empleado.getClaveUsuario(), roles);
	}

}
