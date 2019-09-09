package de.uwepost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.uwepost.dao.ShipRepository;
import de.uwepost.model.Cargo;
import de.uwepost.model.Ship;
import de.uwepost.service.LoadingService;

@Component
public class DatabasePopulator implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

	@Autowired
	private ShipRepository shipRepository;
	
	@Autowired
	private LoadingService loadingService;
	
	@Override
	public void run(String... args) throws Exception {
		String destination = "Singapore";
		Cargo cargo1 = new Cargo("gold",destination,15);
		Cargo cargo2 = new Cargo("silver",destination,10);
		Cargo cargo3 = new Cargo("copper",destination,5);
		
		Ship ship = new Ship("Hamburg",100);
		ship = shipRepository.save(ship);
		
		loadingService.load(ship.getId(), cargo1);
		loadingService.load(ship.getId(), cargo2);
		loadingService.load(ship.getId(), cargo3);
		
		logger.info("created Ship {} ",ship);

	}
	
	

}
