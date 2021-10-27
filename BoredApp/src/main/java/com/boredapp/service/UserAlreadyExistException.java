package com.boredapp.service;

public class UserAlreadyExistException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	
	
	
	public UserAlreadyExistException(String resourceName) {
		super(String.format("the email '%s' alredy exist",resourceName));
		this.resourceName = resourceName;
	
	}
}
