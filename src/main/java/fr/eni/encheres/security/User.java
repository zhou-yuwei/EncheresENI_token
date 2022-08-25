package fr.eni.encheres.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.eni.encheres.bo.Utilisateur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User implements UserDetails {

	//attirbut qui va contenir un membre
	private Utilisateur utilisateur;
	/**
	 * getAuthorities() => doit renyoyer la liste des autorisations de notre utilisateur
	 * si jamais notre membre a l'attribut "admin" à true
	 */
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (utilisateur.isAdministrateur()) {
			return List.of(new SimpleGrantedAuthority("ROLE_admin"));
			}
			return List.of(new SimpleGrantedAuthority("ROLE_user"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.utilisateur.getMotDePasse();
	}

	@Override
	public String getUsername() {
		// 
		return this.utilisateur.getPseudo();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Test ce que le compte est bien non bloqué
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// est ce que l'utilisateur a un mot de passe non expiré
		return true;
	}

	@Override
	public boolean isEnabled() {
		//est ce que l'utilisateur est actif -> tous sont actif
		//on retourne true tout le temps
		return true;
	}

}
