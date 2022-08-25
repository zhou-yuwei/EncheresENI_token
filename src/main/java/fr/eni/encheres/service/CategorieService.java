package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieService {

	public List<Categorie> listeCategories();

	public Categorie getCategorieById(long id);

	public Categorie ajouterCategorie(Categorie categorie);

	public Categorie updateCategorie(Categorie categorie);

	public void deleteCategorieById(Long id);
}
