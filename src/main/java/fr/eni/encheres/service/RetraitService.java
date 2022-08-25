package fr.eni.encheres.service;

import java.util.List;

import fr.eni.encheres.bo.Retrait;

public interface RetraitService {

	Retrait getRetraitById(long id);
	
	public List<Retrait> listeRetraits();

	public Retrait ajouterRetrait(Retrait retrait);

	public Retrait updateRetrait(Retrait retrait);

	public void deleteRetraitById(Long id);
}
