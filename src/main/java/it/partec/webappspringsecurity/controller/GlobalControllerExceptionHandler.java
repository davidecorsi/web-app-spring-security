package it.partec.webappspringsecurity.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import it.partec.webappspringsecurity.dto.Errore;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Errore handleError404(Exception e) {
		e.printStackTrace();
		return new Errore("404", "NOT FOUND");
	}

	@ExceptionHandler(value = { InvalidDefinitionException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Errore invalidDefinitionException(Exception e) {
		e.printStackTrace();
		return new Errore("404", "NOT FOUND");
	}

	@ExceptionHandler(value = { ServletException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Errore servletException(Exception e) {
		e.printStackTrace();
		return new Errore("404", "NOT FOUND");
	}

	@ExceptionHandler(value = { MissingRequestHeaderException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Errore missingRequestHeaderException(Exception e) {
		e.printStackTrace();
		return new Errore("403", "ERRORE NELLA RICHIESTA");
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Errore missingServletRequestParameterException(Exception e) {
		e.printStackTrace();
		return new Errore("400", "BAD REQUEST");
	}

	@ExceptionHandler(value = { HttpMessageConversionException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Errore httpMessageConversionException(Exception e) {
		e.printStackTrace();
		return new Errore("404", "ERRORE NELLA RICHIESTA");
	}

	@ExceptionHandler(value = { EntityNotFoundException.class, EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Errore entityNotFoundException(Exception e) {
		return new Errore("404", "NOT FOUND");
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Errore accessDeniedException(Exception e) {
		return new Errore("403", "FORBIDDEN");
	}
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Errore customizeException(Exception e) {
		return new Errore("503", "ERRORE INTERNO");
	}
}