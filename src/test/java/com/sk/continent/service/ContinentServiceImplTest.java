package com.sk.continent.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sk.continent.ContinentRepository;

/**
 * 
 * @author sdagur
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentServiceImplTest {
	@Mock
    private ContinentRepository repository;
	
	@Autowired
	private ContinentService continentService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
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