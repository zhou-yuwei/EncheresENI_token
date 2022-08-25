package fr.eni.encheres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.repository.ArticleVenduRepository;
import fr.eni.encheres.repository.RetraitRepository;
import fr.eni.encheres.service.ArticleVenduService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class ArticleVenduServiceJPAImpl implements ArticleVenduService {

	@Autowired
	private ArticleVenduRepository articleRepository;
	
	 @Autowired RetraitRepository retraitRepository;

	
	@Override
	public List<ArticleVendu> getArticlesVendu() {
		
		return articleRepository.findAll();
	}

	@Override
	public ArticleVendu getArticleVenduById(long id) {
		
		return articleRepository.findById(id).get();
	}

	@Override
	public ArticleVendu ajouterArticleVendu(ArticleVendu nouvelArticle) {
		
		retraitRepository.save(nouvelArticle.getLieuRetrait());
		
		articleRepository.save(nouvelArticle);
		
		return nouvelArticle;
	}

	@Override
	public List<ArticleVendu> getArticlesParCategorie(long noCategorie) {
		
		return articleRepository.findByCategorieNoCategorie(noCategorie);
	}

}
