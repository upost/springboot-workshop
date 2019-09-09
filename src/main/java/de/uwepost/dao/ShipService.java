package de.uwepost.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uwepost.model.Ship;

@Service
public class ShipService {
	
	private Logger logger = LoggerFactory.getLogger(ShipService.class);
	
	@Autowired
	private ShipRepository shipRepository;

	public boolean moveShipTo(long id, String newLocation) {
		Ship ship=  shipRepository.findById(id).orElse(null);
		if(ship==null) {
			logger.warn("cannot move unknown ship with id {}",id);
			return false;
		}
		ship.setLocation(newLocation);
		shipRepository.save(ship);
		logger.info("{} is now at {}",ship,ship.getLocation());
		return true;
	}

}
