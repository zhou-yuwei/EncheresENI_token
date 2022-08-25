package fr.eni.encheres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
@Autowired
private JwtFilter jwtFilter;

// On définit un bean pour la configuration des routes
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests() // ON AUTORISE
                .antMatchers(HttpMethod.GET, "/**").permitAll() // les requêtes GET
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // les requêtes OPTIONS
                .antMatchers("/api/login").permitAll() // les requêtes pour se loguer
                .anyRequest().authenticated() // sinon, besoin d'être authentifie
                .and().csrf().ignoringAntMatchers("/api/**");
//.formLogin(); commenté car on ne veut pas de redirection vers le formulaire de login
/*****************************************************************
        * AVANT DE FAIRE LA VERIFICATION DE SECURITE, ON AJOUTE UN FILTRE
        * qui va vérifier la présence ou non d'un Json Web Token
        ******************************************************************/
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
