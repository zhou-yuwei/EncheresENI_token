package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurService {

	Utilisateur getUtilisateurById(long id);
	
	Utilisateur getUtilisateurByPseudo(String pseudo);

	Utilisateur getUtilisateurByPseudoAndPassword(String pseudo, String password);

	List<Utilisateur> getUtilisateurs();

	Utilisateur addUtilisateur(Utilisateur utilisateur);

	Utilisateur editUtilistateur(Utilisateur utilistateur);

	void updateUtilisateur(Utilisateur utilisateur);

	void deleteUtilisateurById(Long id);
}
