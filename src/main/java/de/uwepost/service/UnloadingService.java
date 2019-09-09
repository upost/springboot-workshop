package de.uwepost.service;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uwepost.dao.ShipRepository;
import de.uwepost.model.Cargo;
import de.uwepost.model.Ship;

@Service
public class UnloadingService {
	
	@Autowired
	private ShipRepository shipRepository;
	
	private Logger logger = LoggerFactory.getLogger(UnloadingService.class);
	
	public boolean unloadAt(String location) {
		boolean result=false;
		for(Ship ship : shipRepository.findByLocation(location)) {
			result |= unloadCargoWithDestination(ship, location);
		}
		return result;
	}
	
	public boolean unloadCargoWithDestination(Ship ship, String destination) {
		boolean unloadedCargo=false;
		Iterator<Cargo> i = ship.getCargo().iterator();
		while(i.hasNext()) {
			Cargo cargo = i.next();
			if(destination.equalsIgnoreCase(cargo.getTo())) {
				logger.info("unloading {} from {}",cargo,ship);
				i.remove();
				unloadedCargo|=true;
			}
		}
		shipRepository.save(ship);
		return unloadedCargo;
	}

}
