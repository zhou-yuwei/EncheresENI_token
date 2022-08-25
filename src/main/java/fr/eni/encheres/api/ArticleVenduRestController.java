package fr.eni.encheres.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.service.ArticleVenduService;



@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleVenduRestController {

	@Autowired
	private ArticleVenduService articleVenduService;
	
	
	@GetMapping
	public List<ArticleVendu> getArticles(){
		return articleVenduService.getArticlesVendu();
	}
	
	@GetMapping("/{id}")
	public ArticleVendu getArticleById(@PathVariable long id){
		return articleVenduService.getArticleVenduById(id);
	}
	
	@PostMapping
	public ArticleVendu postArticleVendu(@RequestBody ArticleVendu article) {
		
		articleVenduService.ajouterArticleVendu(article);
		return article;
	}
	
	@GetMapping("/categorie/{idCategorie}")
	public List<ArticleVendu> getArticlesByCategorie(@PathVariable long idCategorie){
		return articleVenduService.getArticlesParCategorie(idCategorie);
	}
	

	
	
}
