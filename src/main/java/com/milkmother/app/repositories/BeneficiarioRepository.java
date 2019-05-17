package com.milkmother.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkmother.app.domain.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer>{
	
}
