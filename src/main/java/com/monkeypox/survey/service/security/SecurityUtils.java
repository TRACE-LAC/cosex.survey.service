package com.monkeypox.survey.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.monkeypox.survey.service.security.secrets.SecretsApp;
import com.monkeypox.survey.service.security.secrets.SecretsAppRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityUtils {

	private static final String HEADER = "Authorization";
	
	private static final String PREFIX = "Bearer ";

	private static final String KEY_NAME = "authSecret";

	@Autowired
	private SecretsAppRepository secretsAppRepository;
	
	/**
	 * This method gets the JWT token
	 * @param id: user id
	 * @param docNumber: user document number
	 * @param username
	 * @return JWT token
	 */
	public String getJWTToken(String id, String ip) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId(id + ip)
				.setSubject(ip)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						getSecret().getBytes())
				.compact();

		return token;
	}
    
	/**
	 * This method validates the token
	 * @param request
	 * @return claims
	 */
	public Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(getSecret().getBytes()).parseClaimsJws(jwtToken).getBody();
	}
    
	/**
	 * This method checks JWT token
	 * @param request
	 * @param res: response
	 * @return if is valid the JWT token
	 */
	public boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

	/**
	 * This method gets the auth secret 
	 * @return auth secret 
	 */
	public String getSecret() {
		String value = "";
		SecretsApp secret = secretsAppRepository.findByName(KEY_NAME).get();
		if(secret != null) {
			value = secret.getValue();
		}
		return value;
	}
}
