package fr.eni.encheres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    public Utilisateur getUtilisateurByPseudoAndMotDePasse(String pseudo, String password);

	public Utilisateur getUtilisateurByPseudo(String pseudo);
	
}