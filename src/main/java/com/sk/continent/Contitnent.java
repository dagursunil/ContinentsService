package com.sk.continent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="continent")
public class Contitnent {

	

	private String continentName;
	
	private String continent;

	private List<Country> countries= new ArrayList<>();
	
	
	public List<Country> getCountries() {
		return countries;
	}
	
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
}
