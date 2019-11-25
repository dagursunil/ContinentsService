package com.sk.continent.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author sdagur
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentServiceImplTest {
	@Autowired
    private ContinentService continentService;
	
	@Test
	public void testGetAllFlags() throws IOException {
		List<String> flags=continentService.getAllFlags();
		assertNotNull(flags);
		assertEquals(25, flags.size());
	}
	
	@Test
	public void testGetAllFlagsForContinent() throws IOException {
		List<String> flags=continentService.getAllFlagsForContinent("Asia");
		assertNotNull(flags);
		assertEquals(5, flags.size());
	}
	
	@Test
	public void testGetFlagForCountry() throws IOException {
		String flag=continentService.getFlagForCountry("India");
		assertNotNull(flag);
		
	}
}