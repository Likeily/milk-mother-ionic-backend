package com.milkmother.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkmother.app.domain.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer>{
	
}
