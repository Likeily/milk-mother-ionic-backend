package com.milkmother.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.milkmother.app.domain.Beneficiario;
import com.milkmother.app.domain.Categoria;
import com.milkmother.app.domain.Cidade;
import com.milkmother.app.domain.Estado;
import com.milkmother.app.repositories.BeneficiarioRepository;
import com.milkmother.app.repositories.CategoriaRepository;
import com.milkmother.app.repositories.CidadeRepository;
import com.milkmother.app.repositories.EstadoRepository;

@SpringBootApplication
public class Milkmother1Application {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Milkmother1Application.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

}
