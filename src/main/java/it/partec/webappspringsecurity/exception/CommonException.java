package it.partec.webappspringsecurity.exception;

public class CommonException extends Exception {

	public CommonException(Exception e) {
		super(e);
	}
	
	public CommonException(String message) {
		super(message);
	}	
}