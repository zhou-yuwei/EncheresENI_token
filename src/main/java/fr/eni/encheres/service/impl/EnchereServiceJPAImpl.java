package fr.eni.encheres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.repository.EnchereRepository;
import fr.eni.encheres.service.EnchereService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class EnchereServiceJPAImpl implements EnchereService {

	
	@Autowired
	private EnchereRepository enchereRepository;
	@Override
	public List<Enchere> getEncheres() {
		
		return enchereRepository.findAll();
	}
	@Override
	public Enchere ajouterEnchere(Enchere enchere) {
		
		enchere.setNoArticle(enchere.getArticleEnVente().getNoArticle());
		enchere.setNoEncherisseur(enchere.getEncherisseur().getNoUtilisateur());
		return enchereRepository.save(enchere);
	}
	@Override
	public void supprimerEnchere(Enchere enchere) {
		enchereRepository.delete(enchere);
		
	}

}
