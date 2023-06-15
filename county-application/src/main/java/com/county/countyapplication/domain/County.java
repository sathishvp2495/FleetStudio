package com.county.countyapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class County {
	
	@Id
	String fips;
	String state;
	String name;
		
	public County() {
		super();
	}

	public County(String fips, String state, String name) {
		super();
		this.fips = fips;
		this.state = state;
		this.name = name;
	}
	
	
	public String getFips() {
		return fips;
	}
	public void setFips(String fips) {
		this.fips = fips;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "County [fips=" + fips + ", state=" + state + ", name=" + name + "]";
	}
	
	
	
	

}
