package fr.eni.encheres.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Retrait {
	
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//attributs : rue, code_postal, ville
	private String rue;
	
	private String codePostal;
	
	private String ville;
	
}
