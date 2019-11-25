package sk.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contitnent {

	private String continent;
	private List<Country> countries= new ArrayList<>();
	
	public String getContinent() {
		return continent;
	}
	@JsonSetter("continent")
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public List<Country> getCountries() {
		return countries;
	}
	@JsonSetter("countries")
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
