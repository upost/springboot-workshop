package de.uwepost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.uwepost.service.UnloadingService;

@RestController
public class UnloadController {
	
	@Autowired
	private UnloadingService unloadingService;
	
	@GetMapping("/unload/{location}")
	public boolean load(@PathVariable String location) {
		return unloadingService.unloadAt(location);
	}

}
