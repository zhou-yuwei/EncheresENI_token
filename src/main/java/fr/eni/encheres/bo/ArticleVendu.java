package fr.eni.encheres.bo;

import static fr.eni.encheres.bo.EtatVente.ENCOURS;
import static fr.eni.encheres.bo.EtatVente.NONCOMMENCE;
import static fr.eni.encheres.bo.EtatVente.TERMINE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class ArticleVendu {
	
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noArticle;
	private String nomArticle;
	
	@Column(length = 1000)
    private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateDebutEncheres;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateFinEncheres;
    private int miseAPrix;
    private int prixVente;
    
    @Transient //pas dans BdD
    @JsonIgnore
    private EtatVente etatVente; 
    
    @OneToOne
    private Retrait lieuRetrait;
    
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
   
    @ManyToOne
	@JsonBackReference //si ca marche pas 
    //@JsonIgnore
	@JoinColumn(name = "vendeur_id")
    private Utilisateur vendeur; 

    @ManyToOne
	//@JsonBackReference //si ca marche pas 
    @JsonIgnore
	@JoinColumn(name = "acheteur_id")
    private Utilisateur acheteur; 
    
    @OneToMany
	@JsonIgnore
	@JoinColumn(name = "article_id")
	//@JsonBackReference
    private List<Enchere> encheres;
        


	public ArticleVendu(long noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Retrait lieuRetrait, Categorie categorie,
			Utilisateur vendeur, Utilisateur acheteur) {

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = calculateEtatVente();
		this.lieuRetrait = lieuRetrait;
		this.categorie = categorie;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.encheres = new ArrayList<>();
	}

	
	/**
	 * decider l'EtatVente
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @return EtatVente
	 */
	private EtatVente deciderEtatVente(LocalDate dateDebutEncheres, LocalDate dateFinEncheres) {
		
		LocalDate today = LocalDate.now();
		
		if(today.compareTo(dateDebutEncheres)<0)
			etatVente = NONCOMMENCE;
		else if(today.compareTo(dateFinEncheres)<0)
			etatVente = TERMINE;
		else
			etatVente = ENCOURS;
		
		return etatVente;
	}
	
	public EtatVente calculateEtatVente() {
		
		return deciderEtatVente(getDateDebutEncheres(), getDateFinEncheres());
	}





	
}
