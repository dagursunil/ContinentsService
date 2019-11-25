package sk.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Continents {
	
	private Contitnent[] continents;

	public Contitnent[] getContinents() {
		return continents;
	}

	public void setContinents(Contitnent[] continents) {
		this.continents = continents;
	}

	

}
