package com.milkmother.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkmother.app.domain.Categoria;
import com.milkmother.app.repositories.CategoriaRepository;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositori;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repositori.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
}
