package de.uwepost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.uwepost.model.Cargo;
import de.uwepost.service.LoadingService;

@RestController
public class LoadController {
	
	@Autowired
	private LoadingService loadingService;
	
	@GetMapping("/load/{id}")
	public boolean load(@PathVariable long id, @RequestParam String type, @RequestParam String destination, @RequestParam int weight) {
		return loadingService.load(id, new Cargo(type,destination,weight));
	}

}
