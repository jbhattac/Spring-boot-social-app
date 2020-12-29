package com.jbook.filter;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilterDemoController {
	
	// filter age
		@GetMapping("/filtering-age")
		public MappingJacksonValue retrieveSomeBean() {
			FilterDemoBean filterDemoBean = new FilterDemoBean("Joydeep", "Dak", 100);

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","address");

			FilterProvider filters = new SimpleFilterProvider().addFilter("demoFilter", filter);

			MappingJacksonValue mapping = new MappingJacksonValue(filterDemoBean);

			mapping.setFilters(filters);

			return mapping;
		}

		// filter-address
		@GetMapping("/filtering-address")
		public MappingJacksonValue retrieveListOfSomeBeans() {
			FilterDemoBean filterDemoBean = new FilterDemoBean("Joydeep", "Dak", 100);

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","age");

			FilterProvider filters = new SimpleFilterProvider().addFilter("demoFilter", filter);

			MappingJacksonValue mapping = new MappingJacksonValue(filterDemoBean);

			mapping.setFilters(filters);

			return mapping;
		}


}
