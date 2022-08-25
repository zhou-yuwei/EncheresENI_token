package fr.eni.encheres.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categorie {
	
	//attributs noCategorie, libelle
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noCategorie;
	
	private String libelle;
	
	@OneToMany
	private List<ArticleVendu> articles;
}
