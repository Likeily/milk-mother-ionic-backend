package com.milkmother.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkmother.app.domain.DoacaoSelecionada;

@Repository
public interface DoacaoSelecionadaRepository extends JpaRepository<DoacaoSelecionada, Integer>{
	
}
