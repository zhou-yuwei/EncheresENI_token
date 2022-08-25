package fr.eni.encheres.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtils {
	
private String jwtSecret = "secret";

public String generateJwtToken(Authentication authentication) {
User userPrincipal = (User) authentication.getPrincipal();
return JWT.create().withClaim("pseudo", userPrincipal.getUsername()).sign(Algorithm.HMAC256(jwtSecret));
}
public String getUserNameFromJwtToken(String token) {
return JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token).getClaim("pseudo").asString();
}
public boolean validateJwtToken(String authToken) {
try {
JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(authToken);
return true;
} catch (Exception e) {
System.out.println("error : " + e.getStackTrace());
}
return false;
}
}
