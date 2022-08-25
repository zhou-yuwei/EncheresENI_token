package fr.eni.encheres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Retrait;

public interface RetraitRepository extends JpaRepository<Retrait, Long> {

	
}