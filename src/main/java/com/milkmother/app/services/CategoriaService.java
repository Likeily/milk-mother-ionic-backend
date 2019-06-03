package com.milkmother.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.milkmother.app.domain.Categoria;
import com.milkmother.app.dto.CategoriaDTO;
import com.milkmother.app.repositories.CategoriaRepository;
import com.milkmother.app.services.exceptions.DataIntegrityException;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositori;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repositori.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}

	// o objeto novo a ser inserido tem que ter o id nulo, caso contrário será
	// atualização.
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositori.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositori.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repositori.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivél excluir uma categoria que possui Beneficiários");
		}
	}

	public List<Categoria> findAll() {
		return repositori.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositori.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
