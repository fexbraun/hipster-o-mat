package com.ax.demo.config;

import io.dropwizard.Configuration;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class HipsterConfiguration extends Configuration {

	@NotEmpty
	String conferenceName;

	@Min(2000)
	int year;

	public String getConferenceName() {
		return conferenceName;
	}

	public int getYear() {
		return year;
	}

}
