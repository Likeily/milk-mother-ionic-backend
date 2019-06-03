package com.milkmother.app.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.milkmother.app.domain.Beneficiario;
import com.milkmother.app.dto.BeneficiarioDTO;
import com.milkmother.app.dto.BeneficiarioNewDTO;
import com.milkmother.app.services.BeneficiarioService;

@RestController
@RequestMapping(value="/beneficiarios")
public class BeneficiarioResource {

	@Autowired
	private BeneficiarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Beneficiario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	// Atualiza
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody BeneficiarioDTO objDto, @PathVariable Integer id) {
			Beneficiario obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<BeneficiarioDTO>> findAll() {
			List<Beneficiario> list = service.findAll();
			List<BeneficiarioDTO> listDto = list.stream().map(obj -> new BeneficiarioDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}

		@RequestMapping(value = "/page", method = RequestMethod.GET)
		public ResponseEntity<Page<BeneficiarioDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
			Page<Beneficiario> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<BeneficiarioDTO> listDto = list.map(obj -> new BeneficiarioDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid@RequestBody BeneficiarioNewDTO objDto) {
			Beneficiario obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
}
