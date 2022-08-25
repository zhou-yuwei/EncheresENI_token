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

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.service.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getUsers(){
        return utilisateurService.getUtilisateurs();
    }

//    @PostMapping("/login")
//    public Utilisateur login(@RequestBody Utilisateur u){
//        return utilisateurService.getUtilisateurByPseudoAndPassword(u.getPseudo(), u.getMotDePasse());
//    }

    @PostMapping
    public Utilisateur addUtilisateur(@RequestBody Utilisateur u){
        return utilisateurService.addUtilisateur(u);
    }

//    @PutMapping
//    public Utilisateur editUtilisateur(@RequestBody Utilisateur u) { return utilisateurService.editUtilistateur(u); }
    
	@PutMapping("/{id}")
	public void putUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		if(id != null) {
			utilisateur.setNoUtilisateur(id);
			utilisateurService.updateUtilisateur(utilisateur);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		if(id != null) {
			utilisateurService.deleteUtilisateurById(id);
		
		}
	}
}
