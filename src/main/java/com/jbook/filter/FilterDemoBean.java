package com.jbook.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter(value = "demoFilter")
@ApiModel(description = "describes our dynamic filter demo api")
public class FilterDemoBean {

	private String name;
	
	private String address;
	
	private Integer age;
	
}
