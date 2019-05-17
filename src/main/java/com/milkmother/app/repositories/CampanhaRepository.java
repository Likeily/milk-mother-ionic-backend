package com.milkmother.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkmother.app.domain.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer>{
	
}
