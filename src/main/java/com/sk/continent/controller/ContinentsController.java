package com.sk.continent.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sk.continent.service.ContinentService;

/**
 * 
 * @author sdagur
 *
 */
@RestController
@RequestMapping("continents")
public class ContinentsController {

  @Autowired
  private ContinentService service;
  
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

   @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
  @GetMapping
  public List<String> getAllFlags() throws IOException{
	 LOGGER.info("Inside controller method getAllFlags..");
	 List<String> flags= service.getAllFlags();
	 return flags;
  }
  
  @GetMapping("/{continentName}")
  public ResponseEntity<List<String>> getFlagsForContinents(@PathVariable(value="continentName") String continentName) throws IOException {
	  List<String> flags= service.getAllFlagsForContinent(continentName);
	  LOGGER.info("Inside controller method getFlagsForContinents..");
	  return new ResponseEntity<List<String>>(flags, HttpStatus.CREATED);
	  
  }
  @GetMapping("/country/{countryName}")
  public String getFlagForCountry(@PathVariable(value="countryName") String countryName) throws IOException {
	  LOGGER.info("Inside controller method getFlagForCountry..");
	  return service.getFlagForCountry(countryName);
  }
}
