package com.sk.continent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContinentsApplication  {

  public static void main(String[] args) {
    try {
		SpringApplication.run(ContinentsApplication.class, args);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

