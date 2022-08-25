package fr.eni.encheres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.service.UtilisateurService;

	@Service
	public class MyUserDetailsService implements UserDetailsService {
		
	@Autowired
	private UtilisateurService utilisateurService;	
	
//	public MyUserDetailsService(UtilisateurService utilisateurService) throws Exception {
//		try {
//			utilisateurService.addUtilisateur(new Utilisateur("admin", "admin", true));
//		} catch (Exception e) {
//		new Exception("admin déjà existant");
//		}
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurByPseudo(username);
		if(utilisateur == null) {
		throw new UsernameNotFoundException(username);
		}
		else {
			return new User(utilisateur);
		}
	}
}

