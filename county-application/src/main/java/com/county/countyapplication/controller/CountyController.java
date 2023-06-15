package com.county.countyapplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.county.countyapplication.domain.County;
import com.county.countyapplication.service.CountyService;

@RestController
@RequestMapping("/suggest")
public class CountyController {
	
	private CountyService countyService;

	public CountyController(CountyService countyService) {
		this.countyService = countyService;
	}
	
	@GetMapping("/list")
	public Iterable<County> list(){
		return countyService.list();
	}
	
	@GetMapping("/{name}")
	public Iterable<County> findList(@PathVariable String name){
		if (name.length() == 2) {
            return countyService.findByState(name.toUpperCase());
        } else {
            return countyService.findByName(name);
        }
	}
	
	@GetMapping("/{name}/{state}")
    private List<County> getCountyFromBoth(@PathVariable("name") String name, @PathVariable("state") String state) {
        if (state.length() != 2) {
            state = state + name;
            name = state.substring(0, state.length() - name.length());
            state = state.substring(name.length());
        }
        return countyService.findByStateAndName(name, state);
    }
	
	 @PostMapping("/add/county")
	 private String saveCountry(@RequestBody County county) {
	        countyService.save(county);
	        return "County saved successfully!";
	    }

	 @PostMapping("/add/counties")
	 private String saveCounties(@RequestBody List<County> countyList) {
	        countyService.save(countyList);
	        return "Counties are saved successfully!";
	    }
	
	

}
