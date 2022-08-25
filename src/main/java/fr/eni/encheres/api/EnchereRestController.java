package fr.eni.encheres.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.service.EnchereService;



@RestController
@CrossOrigin
@RequestMapping("/api/enchere")
public class EnchereRestController {

	@Autowired
	private EnchereService enchereService;
	
	
	@GetMapping
	public List<Enchere> getArticles(){
		return enchereService.getEncheres();
	}
	
	
	@PostMapping
	public Enchere postEnchere(@RequestBody Enchere enchere) {
		
		enchereService.ajouterEnchere(enchere);
		return enchere;
	}
	
	@DeleteMapping
	public void deleteEnchere(@RequestBody Enchere enchere) {
		
		enchereService.supprimerEnchere(enchere);

	}
	
	@GetMapping("/article/{idArticle}")
	public List<Enchere> getEncheresByArticle(@PathVariable long idArticle){
		return enchereService.getEncheresParArticle(idArticle);
	}
	
	@GetMapping("/article/enchereGagnante/{idArticle}")
	public Enchere getBestEnchereByArticle(@PathVariable long idArticle){
		return enchereService.getMeilleureEnchereParArticle(idArticle);
	}
	
	
	
}
