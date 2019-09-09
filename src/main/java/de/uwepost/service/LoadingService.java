package de.uwepost.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uwepost.dao.ShipRepository;
import de.uwepost.model.Cargo;
import de.uwepost.model.Ship;

@Service
public class LoadingService {
	
	@Autowired
	private ShipRepository shipRepository;
	
	private Logger logger = LoggerFactory.getLogger(LoadingService.class);
	
	public boolean load(long shipId, Cargo cargo) {
		Ship ship = shipRepository.findById(shipId).orElse(null);
		if(ship==null) {
			logger.warn("cannot load unknown ship with id {}",shipId);
			return false;
		}
		// does the additional cargo fit onto the ship?
		int totalLoad = calculateTotalLoad(ship);
		if(totalLoad+cargo.getWeight()>ship.getCapacity()) {
			logger.warn("cannot add {} to {} ",cargo, ship);
			return false;
		}
		ship.getCargo().add(cargo);
		shipRepository.save(ship);
		logger.info("added {} to {}",cargo,ship);
		return true;
	}

	public int calculateTotalLoad(Ship ship) {
		int total=0;
		for(Cargo c : ship.getCargo()) {
			total+=c.getWeight();
		}
		return total;
	}

}
