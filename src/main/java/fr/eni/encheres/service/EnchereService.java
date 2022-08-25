package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereService {

	public List<Enchere> getEncheres();
	
	public Enchere ajouterEnchere(Enchere enchere);
	
	public void supprimerEnchere(Enchere enchere);
}
