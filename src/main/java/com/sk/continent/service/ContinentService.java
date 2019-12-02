package com.sk.continent.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sk.continent.Contitnent;


/**
 * 
 * @author sdagur
 *
 */
public interface ContinentService  {
  
  List<String> getAllFlags() throws IOException;

  List<String> getAllFlagsForContinent(String continentName) throws IOException;

  String getFlagForCountry(String countryName) throws IOException;

  void createContinent(String jsonString);
}
