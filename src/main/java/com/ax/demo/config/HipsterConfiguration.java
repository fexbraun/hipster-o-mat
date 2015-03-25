package com.ax.demo.config;

import io.dropwizard.Configuration;

import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;

public class HipsterConfiguration extends Configuration {

	@NotEmpty
	private String conferenceName;

	@Min(2000)
	private int year;

	// unfortunate change in Dropwizard 0.8 make this complex view configuration
	// object necessary. Hopefully fixed in next release
	@NotNull
	private ImmutableMap<String, ImmutableMap<String, String>> viewRendererConfiguration = ImmutableMap
			.of();

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

	// unfortunate change in Dropwizard 0.8 make this complex view configuration
	// object necessary. Hopefully fixed in next release
	@JsonProperty("viewRendererConfiguration")
	public ImmutableMap<String, ImmutableMap<String, String>> getViewRendererConfiguration() {
		return viewRendererConfiguration;
	}

	// unfortunate change in Dropwizard 0.8 make this complex view configuration
	// object necessary. Hopefully fixed in next release
	@JsonProperty("viewRendererConfiguration")
	public void setViewRendererConfiguration(
			Map<String, Map<String, String>> viewRendererConfiguration) {
		ImmutableMap.Builder<String, ImmutableMap<String, String>> builder = ImmutableMap
				.builder();
		for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration
				.entrySet()) {
			builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
		}
		this.viewRendererConfiguration = builder.build();
	}

}
