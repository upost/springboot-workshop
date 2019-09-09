package de.uwepost.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.uwepost.Application;
import de.uwepost.dao.ShipService;
import de.uwepost.model.Ship;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Application.class,ShipService.class})
public class ShipServiceTest {

	@Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ShipService shipService;
    
    @Test
    public void testMoveTo() {
    	Ship ship = new Ship("Hamburg",100);
    	ship = entityManager.persist(ship);
    	
    	assertTrue(shipService.moveShipTo(ship.getId(), "Ulm"));
    	assertEquals("Ulm", ship.getLocation());
    }

}
