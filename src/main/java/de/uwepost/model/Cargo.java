package de.uwepost.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cargo {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String type;
	
	private String destination;
	
	private int weight;

	public Cargo() {
		
	}
	
	public Cargo(String type,  String dest, int weight) {
		super();
		this.type = type;
		this.destination = dest;
		this.weight = weight;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTo() {
		return destination;
	}
	public void setTo(String to) {
		this.destination = to;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Cargo "+id+"("+type+"/"+weight+")→"+destination;
	}

	
}
