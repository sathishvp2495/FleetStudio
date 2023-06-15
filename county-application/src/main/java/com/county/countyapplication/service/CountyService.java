package com.county.countyapplication.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.county.countyapplication.domain.County;
import com.county.countyapplication.repository.CountyRepository;

@Service
public class CountyService {
	
	@Autowired
	private CountyRepository countyRepository;
	
	public CountyService(CountyRepository countyRepository) {
		this.countyRepository = countyRepository;
	}
	
	public List<County> findByState(String state){
		return countyRepository.findByState(state)
				.stream()
		        .limit(5)
		        .collect(Collectors.toList());
		
	}
	public List<County> findByName(String name){
		return countyRepository.findByName(name)
				.stream()
		        .limit(5)
		        .collect(Collectors.toList());
	}
	
	public List<County> findByStateAndName(String name,String state){
		return countyRepository.findByStateAndName(name,state)
				.stream()
		        .limit(5)
		        .collect(Collectors.toList());
	}
	
	
	
	public List<County> list(){
		return (List<County>) countyRepository.findAll();
	}
	
	public County save(County county) {
		return countyRepository.save(county);
	}

	public Iterable<County> save(List<County> counties) {
		return countyRepository.saveAll(counties);	
	}

	
}
