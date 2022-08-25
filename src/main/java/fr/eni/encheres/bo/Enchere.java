package fr.eni.encheres.bo;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.eni.encheres.bo.id.EnchereId;
import lombok.Data;

@IdClass(EnchereId.class)
@Data @Entity
public class Enchere {
	
	
	private LocalDateTime dateEnchere;
	
	private int montantEnchere;
	
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id", insertable = false, updatable = false)
	private Utilisateur encherisseur;
	
	
	@ManyToOne
	@JoinColumn(name = "article_id", insertable = false, updatable = false)
	private ArticleVendu articleEnVente;
	
	@Id 
	@Column(name = "utilisateur_id")
	private long noEncherisseur;

	@Id
	@Column(name = "article_id")
	private long noArticle;
	
	
}
