package fr.eni.encheres.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.service.CategorieService;

@RestController
@CrossOrigin
@RequestMapping("/categorie")
public class CategorieRestController {

	@Autowired
	private CategorieService categorieService;
	
	@GetMapping
	public List<Categorie> getCategories(){
		return categorieService.listeCategories();
	}
	
	@GetMapping("/{id}")
	public Categorie getCategorieParId(@PathVariable long id) {
		return categorieService.getCategorieById(id);
	}
	
	@PostMapping
	public Categorie postCategorie(@RequestBody Categorie categorie) {
		categorieService.ajouterCategorie(categorie);
		return categorie;
	}
	
	@PutMapping
	public Categorie putCategorie(@RequestBody Categorie categorie) {
		return categorieService.updateCategorie(categorie);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategorie(@PathVariable Long id) {
		if(id != null) {
			categorieService.deleteCategorieById(id);
		}
	}
}
