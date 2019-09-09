package de.uwepost.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import de.uwepost.model.Ship;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShipIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testShip() {
		
		restTemplate.getForEntity("/ship/launch?location=Stuttgart&capacity=100", Void.class);
		
		Ship[] ships = restTemplate.getForObject("/ships/Stuttgart", Ship[].class);
		
		assertNotNull(ships);
		assertEquals(1,ships.length);
		
		Ship ship=ships[0];
		
		assertEquals("Stuttgart",ship.getLocation());
		assertEquals(100, ship.getCapacity());
		
	}

}
