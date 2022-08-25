package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereService {

	public List<Enchere> getEncheres();
	
	public List<Enchere> getEncheresParArticle(Long noArticle);
	
	public Enchere getMeilleureEnchereParArticle(Long noArticle);
	
	public Enchere ajouterEnchere(Enchere enchere);
	
	public void supprimerEnchere(Enchere enchere);
}
