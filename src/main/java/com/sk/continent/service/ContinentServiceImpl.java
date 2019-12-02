package com.sk.continent.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;
import com.mongodb.util.JSON;
import com.sk.continent.ContinentRepository;
import com.sk.continent.Contitnent;
import com.sk.continent.Country;


/**
 *
 * @author sdagur 
 */
@Service
public class ContinentServiceImpl implements ContinentService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ContinentRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> getAllFlags() throws IOException {
		LOGGER.info("Inside method getAllFlags..");
		List<Contitnent> continents=repository.findAll();
		if(continents!=null && !continents.isEmpty()) {
			List<List<Country>> countries = continents.stream().map(x -> x.getCountries()).distinct()
			.collect(Collectors.toList());
			List<Country> countryList = countries.stream().flatMap(List::stream).collect(Collectors.toList());
			LOGGER.info("Number of countries retrieved.",countryList.size());
			return countryList.stream().map(x -> x.getFlag()).collect(Collectors.toList());
		}
		LOGGER.error("Error in getting flags for the countries");
		return null;
	}

	
	@Override
	public List<String> getAllFlagsForContinent(String continentName) throws IOException {
		LOGGER.info("Inside method getAllFlagsForContinent..");
		Contitnent continent=repository.findByContinent(continentName);
		if(continent!=null) {
		List<Country> countryList = continent.getCountries();
		return countryList.stream().map(x -> x.getFlag()).collect(Collectors.toList());
		}
		LOGGER.error("Error in getting flags for continent",continentName);
		return null;

	}
//
	@Override
	public String getFlagForCountry(String countryName) throws IOException {
		LOGGER.info("Inside method getFlagForCountry..");
		List<Contitnent> continents=repository.findAll();
		List<List<Country>> countries = continents.stream().map(x -> x.getCountries()).distinct()
				.collect(Collectors.toList());
		List<Country> countryList = countries.stream().flatMap(List::stream).collect(Collectors.toList());
		List<String> selectedFlags = countryList.stream().filter(x -> x.getName().equalsIgnoreCase(countryName))
				.map(x -> x.getFlag()).collect(Collectors.toList());
		if (!selectedFlags.isEmpty()) {
			return selectedFlags.get(0);
		}
		LOGGER.error("Error in getting flags for country",countryName);
		return null;
	}
	
	@Override
	
	public void createContinent(String jsonString) {
		LOGGER.info("Inside method createContinent..");
		JSONParser parser = getJsonParser(); 
		Map<String, Object> jsonMap = parser.parseJson(jsonString);
		List<Contitnent> continents=repository.findAll();
		Map<String, Object> continentMap=continents.stream().collect(Collectors.toMap(Contitnent::getContinent,Contitnent::getCountries));
		jsonMap.forEach((key, value) -> {
	        List dbObjects = (List) jsonMap.get(key);
	        for (Object dbObject : dbObjects) {
	          Document document = new Document(parser.parseJson(JSON.serialize(dbObject)));
	          String continent=(String) document.get("continent");
	          if(!continentMap.containsKey(continent)) {
	          mongoTemplate.insert(document, key);
	          }
	        }
	      });
	}
	
	private JSONParser getJsonParser() {
	    JsonParserFactory factory= JsonParserFactory.getInstance();
	    return factory.newJsonParser();
	  }
}