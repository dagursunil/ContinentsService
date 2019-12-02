package com.sk.continent;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sk.continent.Contitnent;

@Repository
public interface ContinentRepository extends MongoRepository <Contitnent, String > {

	Contitnent findByContinent(String continent);
	
	List<Contitnent> findAll();
}
