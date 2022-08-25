package fr.eni.encheres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.id.EnchereId;

public interface EnchereRepository extends JpaRepository<Enchere, EnchereId> {

	
}