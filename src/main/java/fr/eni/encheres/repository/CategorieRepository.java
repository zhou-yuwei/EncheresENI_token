package fr.eni.encheres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

	
}