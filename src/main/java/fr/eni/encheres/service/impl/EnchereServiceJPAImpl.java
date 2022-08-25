package fr.eni.encheres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.EnchereRepository;
import fr.eni.encheres.service.EnchereService;
import fr.eni.encheres.service.UtilisateurService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class EnchereServiceJPAImpl implements EnchereService {

	
	@Autowired
	private EnchereRepository enchereRepository;
	
	@Autowired 
	UtilisateurService utilisateurService;
	
	@Override
	public List<Enchere> getEncheres() {
		
		return enchereRepository.findAll();
	}
	
	@Override
	public Enchere ajouterEnchere(Enchere enchere) {
		//Maj du couple de clés primaire
		enchere.setNoArticle(enchere.getArticleEnVente().getNoArticle());
		enchere.setNoEncherisseur(enchere.getEncherisseur().getNoUtilisateur());
		
		//Recuperer l'utilisateur qui fait l'enchère
		Utilisateur encherisseur = utilisateurService.getUtilisateurById(enchere.getNoEncherisseur());
		
		//reperer montant enchère
		int montantEnchere = enchere.getMontantEnchere();
		
		//verifier que la nouvelle enchère est supérieure à la meilleure enchere
		if (verifierMeilleureEnchereActuelle(enchere)) {
			
			//verifier le solde de l'acquereur
			System.out.println("montant enchere : " + montantEnchere +" nom : "+encherisseur.getPseudo() + " credit " + encherisseur.getCredit());
			if(verifierSoldeAcquereur(montantEnchere, encherisseur)) {
				
				//recuperer le n° d'article
				long noArticle = enchere.getArticleEnVente().getNoArticle();
			
				//Recuperer la meilleure enchère actuelle
				Enchere meilleureEnchere = enchereRepository.findTop1ByArticleEnVenteNoArticleOrderByMontantEnchereDesc(noArticle);
				
				
				Utilisateur exMeilleurEncherisseur=null;
				// recuperer le meilleur acheteur actuel
				if (meilleureEnchere!=null) {
					exMeilleurEncherisseur = meilleureEnchere.getEncherisseur();
					
					//créditer l'ancien meilleur encherisseur
					editerSoldeUtilisateur(exMeilleurEncherisseur, meilleureEnchere.getMontantEnchere());
				}else {
					System.out.println("Ceci est la première enchère pour cette objet");
				}
				
				//debiter le nouvel meilleur encherisseur
				editerSoldeUtilisateur(encherisseur, - montantEnchere);
				
				//ajout de l'enchere dans la bdd
				enchereRepository.save(enchere);
			}
						
		}else {
			System.out.println("Erreur, l'enchère n'a pas été ajoutée");
		}
				
		return enchere;
	}

	@Override
	public void supprimerEnchere(Enchere enchere) {
		enchereRepository.delete(enchere);
		
	}
	
	@Override
	public List<Enchere> getEncheresParArticle(Long noArticle) {
		
		return enchereRepository.findByArticleEnVenteNoArticleOrderByMontantEnchereDesc(noArticle);
	}
	
	@Override
	public Enchere getMeilleureEnchereParArticle(Long noArticle) {
		
		return enchereRepository.findTop1ByArticleEnVenteNoArticleOrderByMontantEnchereDesc(noArticle);
	}
	
	/**
	 * Vérifie que le montant de la nouvelle enchère est supérieure à l'enchère actuelle la plus haute
	 * @param enchereAVerifier
	 * @return
	 */
	public boolean verifierMeilleureEnchereActuelle(Enchere enchereAVerifier) {
	
		long noArticle = enchereAVerifier.getNoArticle();
				
		int montantMeilleureEnchere = 0;
		
		Enchere meilleureEnchere = enchereRepository.findTop1ByArticleEnVenteNoArticleOrderByMontantEnchereDesc(noArticle);
		
		//Recuperer le montant de la meilleure enchère
		if (meilleureEnchere!=null)
			{montantMeilleureEnchere = meilleureEnchere.getMontantEnchere();}
		
		//vérifie si la nouvelle enchere est supérieure à la meilleure enchere en cours
		boolean result = enchereAVerifier.getMontantEnchere() > montantMeilleureEnchere;
		
		if(!result) {
			System.out.println("L'enchère est inférieure à la meilleure enchère en cours, veuillez faire une enchère plus élevée");

		}
		return result;
	}
	
	/**
	 * Vérifie que le solde de l'utilisateur est suffisant pour enchérir
	 * @param montantEnchere
	 * @param acquereur
	 * @return true si solde suffisant, false sinon
	 */
	public boolean verifierSoldeAcquereur(int montantEnchere,Utilisateur acquereur) {
		 boolean result = acquereur.getCredit() >= montantEnchere;
		 if (!result) {
			 System.out.println("Solde insufisant");
		 }
		return result;
	}
	
	/**
	 * Créditer ou débiter l'utilisateur
	 * 
	 * @param acquereur
	 * @param montant positif si crédit, négatif si débit
	 */
	public void editerSoldeUtilisateur(Utilisateur acquereur, int montant) {
		
		System.out.println("Editer solde de : " + acquereur.getPrenom() + " " + acquereur.getNom() + " d'un montant de : " + montant);
		
		int nouveauSolde = acquereur.getCredit() + montant;
		
		acquereur.setCredit(nouveauSolde);
		
		utilisateurService.editUtilistateur(acquereur);
	}
}
