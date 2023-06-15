package com.county.countyapplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.county.countyapplication.domain.County;
import com.county.countyapplication.service.CountyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class CountyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountyApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner runner(CountyService countyService) {
	    return args -> {
				// read JSON and load json
				ObjectMapper mapper = new ObjectMapper();
				// we want  list of users
				TypeReference<List<County>> typeReference = new TypeReference<List<County>>(){};
				// input stream is read the json file
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
				try {
					List<County> county = mapper.readValue(inputStream,typeReference);
					countyService.save(county);
					System.out.println("county Saved!");
				} catch (IOException e){
					System.out.println("Unable to save counties: " + e.getMessage());
				}
	    };
			
	}

}
