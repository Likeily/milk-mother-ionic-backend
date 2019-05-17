package com.milkmother.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkmother.app.domain.Usuario;
import com.milkmother.app.repositories.UsuarioRepository;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositori;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repositori.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id
					+ ", Tipo: " + Usuario.class.getName());
		}
		return obj.orElse(null);
	}
}
