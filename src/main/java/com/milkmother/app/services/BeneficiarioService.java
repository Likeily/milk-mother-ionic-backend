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

import com.milkmother.app.domain.Beneficiario;
import com.milkmother.app.domain.Cidade;
import com.milkmother.app.domain.Endereco;
import com.milkmother.app.dto.BeneficiarioDTO;
import com.milkmother.app.dto.BeneficiarioNewDTO;
import com.milkmother.app.enums.TipoBeneficiario;
import com.milkmother.app.repositories.BeneficiarioRepository;
import com.milkmother.app.repositories.EnderecoRepository;
import com.milkmother.app.services.exceptions.DataIntegrityException;
import com.milkmother.app.services.exceptions.ObjectNotFoundException;

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository repositori;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Beneficiario find(Integer id) {
		Optional<Beneficiario> obj = repositori.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id
					+ ", Tipo: " + Beneficiario.class.getName());
		}
		return obj.orElse(null);
	}
	
	@Transactional
	public Beneficiario insert(Beneficiario obj) {
		obj.setId(null);
		obj = repositori.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	/*O objeto é instanciado pelo BD pois aí é monitorado pelo JPA,
	  depois o objeto é atualizado com o objeto que foi enviado na requisição
	  e logo em seguida é salvo no BD.*/
	public Beneficiario update(Beneficiario obj) {
		Beneficiario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositori.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repositori.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivél excluir uma Usuário que possui Doações ativas");
		}
	}
	
	public List<Beneficiario> findAll(){
		return repositori.findAll();
	}
	
	public Page<Beneficiario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositori.findAll(pageRequest);
	}
	
	public Beneficiario fromDTO(BeneficiarioDTO objDto) {
		return new Beneficiario(objDto.getId(), objDto.getNome(), null, null, null);
	}
	
	public Beneficiario fromDTO(BeneficiarioNewDTO objDto) {
		Beneficiario ben = new Beneficiario(null, objDto.getNome(), objDto.getValorParaDoacao(), objDto.getCpf(), TipoBeneficiario.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), null, cid, ben);
		ben.getEnderecos().add(end);
		
		return ben;
		
	}
	
	private void updateData(Beneficiario newObj, Beneficiario obj) {
		newObj.setNome(obj.getNome());
	}
}
