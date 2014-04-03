
package com.ax.bedcon.config;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class HipsterConfiguration extends Configuration
{

	@NotEmpty
	String anotherString;
	
	public String getAnotherString() {
		return anotherString;
	}
}
