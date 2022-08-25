package fr.eni.encheres.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.repository.RetraitRepository;
import fr.eni.encheres.service.RetraitService;
import lombok.NoArgsConstructor;


@Service
@Profile("prod")
@NoArgsConstructor
public class RetraitServiceJPAImpl implements RetraitService {

	
	@Autowired
	private RetraitRepository retraitRepository;
	
	@Override
	public Retrait getRetraitById(long id) {
		
		return retraitRepository.findById(id).get();
	}

	@Override
	public List<Retrait> listeRetraits() {
		
		return retraitRepository.findAll();
	}


	@Override
	public Retrait ajouterRetrait(Retrait retrait) {
		
		retraitRepository.save(retrait);
		return retrait;
		
	}

	@Override
	public Retrait updateRetrait(Retrait retrait) {
		return this.ajouterRetrait(retrait);
	}

	@Override
	public void deleteRetraitById(Long id) {
		retraitRepository.deleteById(id);
		
	}

}
