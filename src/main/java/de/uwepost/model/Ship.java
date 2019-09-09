package de.uwepost.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes= {@Index(columnList="location")})
public class Ship {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Cargo> cargo;

	private String location;
	
	
	private int capacity;
	
	public Ship() {
	
	}
	
	public Ship(String location, int capacity) {
		this.location = location;
		this.capacity = capacity;
		cargo = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Cargo> getCargo() {
		return cargo;
	}

	public void setCargo(List<Cargo> cargo) {
		this.cargo = cargo;
	}


	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Ship "+id+ "@"+location + "(cap "+ capacity+")";
	}



	
}
