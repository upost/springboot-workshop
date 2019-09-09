package de.uwepost.dao;

import org.springframework.data.repository.CrudRepository;

import de.uwepost.model.Ship;

public interface ShipRepository extends CrudRepository<Ship, Long> {
	Iterable<Ship> findByLocation(String location);
}
