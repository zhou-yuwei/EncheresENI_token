package fr.eni.encheres.bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noUtilisateur;

	@Column(unique=true)
	private String pseudo;
	
	private String nom;
	
	private String prenom;
	
	@Column(unique=true)
	private String email;
	
	private String telephone;
	
	private String rue;

	@Column(length = 5)
	private String codePostal;
	
	private String ville;
	
	private String motDePasse;
	
	private int credit;
	
	private boolean administrateur;

	
	
	//TODO se mettre d'accord sur le sens de la relation
	@OneToMany
	@JoinColumn(name = "acheteur_id")
//	@JsonIgnore
	@JsonManagedReference

	//spécifie dans une relation bidirectionnelle (ici Utilisateur <-> ArticleVendu) c'est cet attribut qui va contenir le JSON de la relation (=liste des articles au format JSON)
	//Cela ne peut se faire que dans un sens sinon on a une boucle infinie de référence
	private List<ArticleVendu> achats;
	
	@OneToMany
	@JoinColumn(name = "vendeur_id")
//	@JsonIgnore
	@JsonManagedReference

	//spécifie dans une relation bidirectionnelle (ici Utilisateur <-> ArticleVendu) c'est cet attribut qui va contenir le JSON de la relation (=liste des articles au format JSON)
	//Cela ne peut se faire que dans un sens sinon on a une boucle infinie de référence
	private List<ArticleVendu> ventes;
	
	/**
	 * @OneToMany : Un utilisateur peut avoir plusieurs enchères
	 */
	@OneToMany
	/**
	 * @JsonBackReference spécifie que dans une relation bidirectionnelle cet attribut film 
	 * ne doit pas être inclus dans le JSON complet de la relation (sinon boucle infinie)
	 * 
	 */
	@JsonBackReference
	//@JsonIgnore
	private List<Enchere> encheres;
	
	public Utilisateur(String pseudo, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}

	public Utilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur ) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	
	
	

}
