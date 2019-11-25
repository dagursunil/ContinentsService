package com.sk.continent.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import sk.beans.Contitnent;
import sk.beans.Country;

/**
 *
 * @author sdagur 
 */
@Service
public class ContinentServiceImpl implements ContinentService {

	private List<Contitnent> continentsList;

	private List<Contitnent> continentsFile() throws IOException {
		// read json file data to String
		String path = Paths.get("").toAbsolutePath().toString() + "/" + "continents.json";
		byte[] jsonData = Files.readAllBytes(Paths.get(path));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		Contitnent[] continents = objectMapper.readValue(jsonData, Contitnent[].class);
		continentsList = Arrays.asList(continents);
		// configure Object mapper for pretty print
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return continentsList;
	}

	@Override
	public List<String> getAllFlags() throws IOException {
		List<List<Country>> countries = getCountryList();
		List<Country> countryList = countries.stream().flatMap(List::stream).collect(Collectors.toList());
		return countryList.stream().map(x -> x.getFlag()).collect(Collectors.toList());
	}

	private List<List<Country>> getCountryList() throws IOException {
		if (continentsList == null || continentsList.isEmpty()) {
			continentsList = continentsFile();
		}
		List<List<Country>> countries = continentsList.stream().map(x -> x.getCountries()).distinct()
				.collect(Collectors.toList());
		return countries;
	}

	@Override
	public List<String> getAllFlagsForContinent(String continentName) throws IOException {
		if (continentsList == null || continentsList.isEmpty()) {
			continentsList = continentsFile();
		}
		List<List<Country>> countries = continentsList.stream()
				.filter(x -> x.getContinent().equalsIgnoreCase(continentName)).map(x -> x.getCountries()).distinct()
				.collect(Collectors.toList());
		List<Country> countryList = countries.stream().flatMap(List::stream).collect(Collectors.toList());
		return countryList.stream().map(x -> x.getFlag()).collect(Collectors.toList());
	}

	@Override
	public String getFlagForCountry(String countryName) throws IOException {
		List<List<Country>> countries = getCountryList();
		List<Country> countryList = countries.stream().flatMap(List::stream).collect(Collectors.toList());
		List<String> selectedFlags = countryList.stream().filter(x -> x.getName().equalsIgnoreCase(countryName))
				.map(x -> x.getFlag()).collect(Collectors.toList());
		if (!selectedFlags.isEmpty()) {
			return selectedFlags.get(0);
		}
		return null;
	}
}
