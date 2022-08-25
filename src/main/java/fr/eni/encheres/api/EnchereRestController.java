package fr.eni.encheres.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.service.EnchereService;



@RestController
@CrossOrigin
@RequestMapping("/enchere")
public class EnchereRestController {

	@Autowired
	private EnchereService enchereVenduService;
	
	
	@GetMapping
	public List<Enchere> getArticles(){
		return enchereVenduService.getEncheres();
	}
	
	
	@PostMapping
	public Enchere postEnchere(@RequestBody Enchere enchere) {
		
		enchereVenduService.ajouterEnchere(enchere);
		return enchere;
	}
	
	@DeleteMapping
	public void deleteEnchere(@RequestBody Enchere enchere) {
		
		enchereVenduService.supprimerEnchere(enchere);

	}
	

	
	
}
