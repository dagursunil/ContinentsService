package com.sk.continent.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private ContinentService accountService;

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
	 List<String> flags= accountService.getAllFlags();
	 return flags;
  }
  
  @GetMapping("/{continentName}")
  public ResponseEntity<List<String>> getFlagsForContinents(@PathVariable(value="continentName") String continentName) throws IOException {
	  List<String> flags= accountService.getAllFlagsForContinent(continentName);
	  return new ResponseEntity<List<String>>(flags, HttpStatus.CREATED);
	  
  }
  @GetMapping("/country/{countryName}")
  public String getFlagForCountry(@PathVariable(value="countryName") String countryName) throws IOException {
	  return accountService.getFlagForCountry(countryName);
  }
}
