package fr.eni.encheres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduRepository extends JpaRepository<ArticleVendu, Long> {

	
	/**
	 * Je rajoute une signature de méthode qui RESPECTE la CONVENTION des Queries Method
	 * - Ca doit commencer par findBy ou existsBy
	 * - Ensuite on met le nom de l'attribut (avec 1ère lettre majuscule) sur lequel on va faire la recherche
	 * 
	 * => la méthode va être créée et va effectuer la recherche voulue
	 */
	
	// méthode qui renvoie un genre à partir du libellé
	public List<ArticleVendu> findByCategorieNoCategorie(long noCategorie);
	

	
}