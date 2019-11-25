package com.sk.continent.service;

import java.io.IOException;
import java.util.List;


/**
 * 
 * @author sdagur
 *
 */
public interface ContinentService  {
  
  List<String> getAllFlags() throws IOException;

  List<String> getAllFlagsForContinent(String continentName) throws IOException;

  String getFlagForCountry(String countryName) throws IOException;
}
