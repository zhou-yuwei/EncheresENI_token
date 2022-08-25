package fr.eni.encheres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.UtilisateurRepository;
import fr.eni.encheres.service.UtilisateurService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class UtilisateurServiceJPAImpl implements UtilisateurService {

	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Utilisateur getUtilisateurById(long id) {
		
		return utilisateurRepository.findById(id).get();
	}

	@Override
	public Utilisateur getUtilisateurByPseudoAndPassword(String pseudo, String password) {
		Utilisateur utilisateur = utilisateurRepository.getUtilisateurByPseudoAndMotDePasse(pseudo, password);
		if(utilisateur != null){
			return utilisateur;
		}
		return null;
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur editUtilistateur(Utilisateur utilistateur) {
		return this.addUtilisateur(utilistateur);
	}

	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		Utilisateur utilisateur = utilisateurRepository.getUtilisateurByPseudo(pseudo);
		if(utilisateur != null){
			return utilisateur;
		}
		return null;
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
		
	}

	@Override
	public void deleteUtilisateurById(Long id) {
		utilisateurRepository.deleteById(id);
		
	}


}
