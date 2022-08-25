package fr.eni.encheres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.repository.CategorieRepository;
import fr.eni.encheres.service.CategorieService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class CategorieServiceJPAImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Override
	public List<Categorie> listeCategories() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorieById(long id) {
		
		return categorieRepository.findById(id).get();
	}

	@Override
	public Categorie ajouterCategorie(Categorie categorie) {
		
		categorieRepository.save(categorie);
		return categorie;
		
	}

	@Override
	public Categorie updateCategorie(Categorie categorie) {
		return this.ajouterCategorie(categorie);
	}

	@Override
	public void deleteCategorieById(Long id) {
		categorieRepository.deleteById(id);
		
	}

}
