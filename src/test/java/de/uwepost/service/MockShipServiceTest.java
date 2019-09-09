package de.uwepost.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.uwepost.dao.ShipRepository;
import de.uwepost.dao.ShipService;
import de.uwepost.model.Ship;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShipService.class)
public class MockShipServiceTest {
	
	@Autowired
	private ShipService shipService;
	
	@MockBean
	private ShipRepository shipRepository;
	
	@Test
	public void testMoveShip() {
		Ship ship = new Ship("Hamburg",100);
		ship.setId(1);
		
		given(shipRepository.findById(1L)).willReturn(Optional.of(ship));
		
		assertTrue(shipService.moveShipTo(1, "Ulm"));
		
		assertEquals("Ulm", ship.getLocation());
		
	}

}
