package com.ax.demo.config;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class HipsterConfiguration extends Configuration {

	@NotEmpty
	private String conferenceName;

	@Min(2000)
	private int year;

	public String getConferenceName() {
		return conferenceName;
	}

	@VisibleForTesting
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public int getYear() {
		return year;
	}

	
	@JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
  
	
	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		return swaggerBundleConfiguration;
	}
	
	

}
