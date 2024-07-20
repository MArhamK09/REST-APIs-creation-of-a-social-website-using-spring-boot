package com.restapi.learnrestapispring;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@RestController
public class FilteringControllerClass {
	
	@GetMapping(path="/filter",produces=MediaType.APPLICATION_JSON_VALUE)
	public MappingJacksonValue filtering() {
		SomeBean bean=new SomeBean("val1","val2","val3");
		MappingJacksonValue mapping=new MappingJacksonValue(bean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter );
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping(path="/filter-list",produces=MediaType.APPLICATION_JSON_VALUE)
	public MappingJacksonValue filterList(){
		List<SomeBean> lst=Arrays.asList(new SomeBean("val1","val2","val3"),
				new SomeBean("val11","val22","val33"),
				new SomeBean("val111","val222","val333"));
		MappingJacksonValue mappingJson=new MappingJacksonValue(lst);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter );
		mappingJson.setFilters(filters);
		
		return mappingJson;
	}

}
