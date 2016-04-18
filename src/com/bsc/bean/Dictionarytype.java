package com.bsc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Dictionarytype entity. @author MyEclipse Persistence Tools
 */

public class Dictionarytype implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String description;
	private Set dictionaries = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dictionarytype() {
	}

	/** minimal constructor */
	public Dictionarytype(String id) {
		this.id = id;
	}

	/** full constructor */
	public Dictionarytype(String id, String name, String description,
			Set dictionaries) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dictionaries = dictionaries;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getDictionaries() {
		return this.dictionaries;
	}

	public void setDictionaries(Set dictionaries) {
		this.dictionaries = dictionaries;
	}

}