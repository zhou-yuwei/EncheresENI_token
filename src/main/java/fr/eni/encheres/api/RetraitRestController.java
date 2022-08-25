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

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.service.RetraitService;

@RestController
@CrossOrigin
@RequestMapping("/retrait")
public class RetraitRestController {

	@Autowired
	private RetraitService retraitService;
	
	@GetMapping
	public List<Retrait> getRetraits(){
		return retraitService.listeRetraits();
	}
	
	@GetMapping("/{id}")
	public Retrait getRetraitParId(@PathVariable long id) {
		return retraitService.getRetraitById(id);
	}
	
	@PostMapping
	public Retrait postRetrait(@RequestBody Retrait retrait) {
		retraitService.ajouterRetrait(retrait);
		return retrait;
	}
	
	@PutMapping
	public Retrait putRetrait(@RequestBody Retrait retrait) {
		return retraitService.updateRetrait(retrait);
	}
	
	@DeleteMapping("/{id}")
	public void deleteRetrait(@PathVariable Long id) {
		if(id != null) {
			retraitService.deleteRetraitById(id);
		}
	}
}
