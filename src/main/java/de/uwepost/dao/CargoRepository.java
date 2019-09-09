package de.uwepost.dao;

import org.springframework.data.repository.CrudRepository;

import de.uwepost.model.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Long> {

}
