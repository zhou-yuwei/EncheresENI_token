package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduService {
	
	public List<ArticleVendu> getArticlesVendu();

	public ArticleVendu getArticleVenduById(long id);
	
	public ArticleVendu ajouterArticleVendu(ArticleVendu nouvelArticle);
	
	public List<ArticleVendu> getArticlesParCategorie(long noCategorie);
}
