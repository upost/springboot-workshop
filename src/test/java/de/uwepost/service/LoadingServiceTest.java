package de.uwepost.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.uwepost.Application;
import de.uwepost.model.Cargo;
import de.uwepost.model.Ship;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Application.class,LoadingService.class})
public class LoadingServiceTest {
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private LoadingService loadingService;

    @Test
	public void testLoad() {
    	Ship ship = new Ship("Hamburg",100);
        entityManager.persist(ship);
        
        assertFalse(loadingService.load(736845, new Cargo("test","dest",900)));
        assertFalse(loadingService.load(ship.getId(), new Cargo("test","dest",900)));
        assertTrue(loadingService.load(ship.getId(), new Cargo("test","dest",90)));
	}

	@Test
	public void testCalculateTotalLoad() {
		Ship ship = new Ship("Hamburg",100);
		
		assertEquals(0,loadingService.calculateTotalLoad(ship));
		ship.getCargo().add(new Cargo("test","test",50));
		assertEquals(50,loadingService.calculateTotalLoad(ship));
	}

}
