package com.boredapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

@SuppressWarnings("serial")
public class CategoryKey implements Serializable{

	/**
	 * 
	 */
	private String activity;
	
	private String category;
	
}
