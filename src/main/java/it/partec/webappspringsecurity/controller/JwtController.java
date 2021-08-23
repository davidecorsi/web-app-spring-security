package it.partec.webappspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.partec.webappspringsecurity.dto.JwtRequest;
import it.partec.webappspringsecurity.dto.JwtResponse;
import it.partec.webappspringsecurity.service.JwtService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch(DisabledException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch(BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		UserDetails userDetails = userDetailsService
				.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtService.generateToken(userDetails);
		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
	}
}
