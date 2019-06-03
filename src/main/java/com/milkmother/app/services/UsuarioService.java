package com.milkmother.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.milkmother.app.domain.Cidade;
import com.milkmother.app.domain.Endereco;
import com.milkmother.app.domain.Usuario;
import com.milkmother.app.dto.UsuarioDTO;
import com.milkmother.app.dto.UsuarioNewDTO;
import com.milkmother.app.enums.TipoUsuario;
import com.milkmother.app.repositories.EnderecoRepository;
import com.milkmother.app.repositories.UsuarioRepository;
import com.milkmother.app.services.exceptions.DataIntegrityException;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositori;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repositori.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repositori.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositori.save(newObj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repositori.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public List<Usuario> findAll() {
		return repositori.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositori.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoUsuario.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), user, cid, null);
		user.getEnderecos().add(end);
		user.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			user.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			user.getTelefones().add(objDto.getTelefone3());
		}
		return user;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}