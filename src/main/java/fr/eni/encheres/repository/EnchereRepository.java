package fr.eni.encheres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.id.EnchereId;

public interface EnchereRepository extends JpaRepository<Enchere, EnchereId> {

	public List<Enchere> findByArticleEnVenteNoArticleOrderByMontantEnchereDesc(long noArticle);
	
	public Enchere findTop1ByArticleEnVenteNoArticleOrderByMontantEnchereDesc(long noArticle);
}