package it.partec.webappspringsecurity.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	public String getUsernameFromToken(String token);
	public Date getExpirationDateFromToken(String token);
	public String generateToken(UserDetails userDetails);
	public Boolean validateToken(String token, UserDetails userDetails);
}
