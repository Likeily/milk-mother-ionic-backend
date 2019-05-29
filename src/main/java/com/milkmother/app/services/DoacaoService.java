package com.milkmother.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkmother.app.domain.Doacao;
import com.milkmother.app.repositories.DoacaoRepository;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class DoacaoService {

	@Autowired
	private DoacaoRepository repositori;
	
	public Doacao find(Integer id) {
		Optional<Doacao> obj = repositori.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id
					+ ", Tipo: " + Doacao.class.getName());
		}
		return obj.orElse(null);
	}
}
