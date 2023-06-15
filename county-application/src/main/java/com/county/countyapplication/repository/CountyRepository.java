package com.county.countyapplication.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.county.countyapplication.domain.County;

public interface CountyRepository extends CrudRepository<County, String> {
	
	
	
	@Query("SELECT c FROM County c WHERE c.name like %?1% and c.state like %?2%") 
	List<County> findByStateAndName(String name,String state);
	
	
	@Query("SELECT c FROM County c WHERE c.name like %?1%") 
	List<County> findByName(String name);
	
	
	@Query("SELECT c FROM County c WHERE c.state like %?1%")
	List<County> findByState(String state);
	
	
	
	
	



}
