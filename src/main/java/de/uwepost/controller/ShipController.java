package de.uwepost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uwepost.dao.ShipRepository;
import de.uwepost.dao.ShipService;
import de.uwepost.model.Ship;

@RestController
public class ShipController {
	
	@Autowired
	private ShipRepository shipRepository;
	
	@Autowired
	private ShipService shipService;
	
	@GetMapping("ship/{id}")
	public Ship get(@PathVariable long id) {
		return shipRepository.findById(id).orElse(null);
	}
	
	@GetMapping("ships")
	@Cacheable
	public Iterable<Ship> get() {
		return shipRepository.findAll();
	}
	
	@GetMapping("ships/{location}")
	public Iterable<Ship> get(@PathVariable String location) {
		return shipRepository.findByLocation(location);
	}
	
	@GetMapping("ship/launch")
	public void launch(@RequestParam String location, @RequestParam int capacity) {
		shipRepository.save(new Ship(location,capacity));
	}
	
	@GetMapping("ship/{id}/moveto/{location}")
	public boolean move(@PathVariable long id, @PathVariable String location) {
		return shipService.moveShipTo(id, location);
	}
	
	

}
