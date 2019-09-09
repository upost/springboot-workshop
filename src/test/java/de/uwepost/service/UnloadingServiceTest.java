package de.uwepost.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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
@ContextConfiguration(classes = {Application.class,UnloadingService.class})
public class UnloadingServiceTest {
	
	@Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UnloadingService unloadingService;
    
        
    @Test
    public void testUnloadAt() {
    	Ship ship = new Ship("Hamburg",100);
    	ship.getCargo().add(new Cargo("Beans","Hamburg",40));
    	ship.getCargo().add(new Cargo("Cables","Ulm",10));
        entityManager.persist(ship);
        // do not unload anything at Ulm because ship is somewhere else
        assertFalse(unloadingService.unloadAt("Ulm"));
        assertEquals(2,ship.getCargo().size());
        
        // unload at Hamburg and keep Cables
        assertTrue(unloadingService.unloadAt("Hamburg"));
        assertEquals(1,ship.getCargo().size());
        assertEquals("Cables",ship.getCargo().get(0).getType());
    	
    }

    @Test
    public void testUnloadCargoWithDestination() {
    	Ship ship = new Ship("Hamburg",100);
    	ship.getCargo().add(new Cargo("Bananas","Hamburg",20));
    	ship.getCargo().add(new Cargo("Cables","Ulm",10));
        entityManager.persist(ship);
        assertTrue(unloadingService.unloadCargoWithDestination(ship, "Hamburg"));
        assertEquals(1,ship.getCargo().size());
        assertEquals("Cables",ship.getCargo().get(0).getType());
    }
    

}
