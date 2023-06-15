package com.county.countyapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.county.countyapplication.controller.CountyController;
import com.county.countyapplication.domain.County;
import com.county.countyapplication.repository.CountyRepository;
import com.county.countyapplication.service.CountyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = County.class)
class CountyServiceTest {

	@InjectMocks
	private CountyService countyService;

	@Mock
	private CountyRepository countyRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.openMocks(this);
		County county = new County();
		county.setFips("01001");
		county.setName("Autauga");
		county.setState("AL");
		countyRepository.save(county);

		County county1 = new County();
		county1.setFips("01003");
		county1.setName("Baldwin");
		county1.setState("AL");
		countyRepository.save(county1);

		County county2 = new County();
		county2.setFips("01005");
		county2.setName("Barbour");
		county2.setState("AL");
		countyRepository.save(county2);

		County county3 = new County();
		county3.setFips("01007");
		county3.setName("Bibb");
		county3.setState("AL");
		countyRepository.save(county3);

		List<County> countyList = new ArrayList<County>();
		countyList.add(county);
		countyList.add(county1);
		countyList.add(county2);
	}

	List<County> countyList = new ArrayList<County>();

	String countiesString = "[\r\n" + "  { \"fips\": \"01001\", \"state\": \"AL\", \"name\": \"Autauga\" },\r\n"
			+ "  { \"fips\": \"01003\", \"state\": \"AL\", \"name\": \"Baldwin\" },\r\n"
			+ "  { \"fips\": \"01005\", \"state\": \"AL\", \"name\": \"Barbour\" },\r\n"
			+ "  { \"fips\": \"01007\", \"state\": \"AL\", \"name\": \"Bibb\" }\r\n" + "]";

	@Test
	public void testGetAllUsers() {
		// Mocking the countyRepository behavior
		List<County> mockCounty = new ArrayList<>();
		mockCounty.add(new County("01001", "AL", "Autauga"));
		mockCounty.add(new County("01003", "AL", "Baldwin"));

		Mockito.when(countyRepository.findAll()).thenReturn(mockCounty);

		List<County> counties = (List<County>) ((CountyController) mockCounty).list();

		Assertions.assertEquals(2, counties.size());

		Assertions.assertEquals("01001", counties.get(0).getFips());

		Mockito.verify(countyRepository).findAll();
	}

	@Test
	void saveCountyTest() {
		Mockito.when(countyRepository.saveAll(countyList)).thenReturn(countyList);
		countyService.save(countyList);
	}

	@Test
	void saveAllCountyTest() {
		Mockito.when(countyRepository.saveAll(countyList)).thenReturn(countyList);
		assertEquals(countyList.toString(), countiesString);
	}

}