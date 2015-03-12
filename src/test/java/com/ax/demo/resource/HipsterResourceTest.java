package com.ax.demo.resource;

import static org.junit.Assert.assertEquals;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;
import org.junit.ClassRule;
import org.junit.Test;

import com.ax.demo.HipsterApplication;
import com.ax.demo.config.HipsterConfiguration;
import com.ax.demo.entity.Hipster;
import com.ax.demo.entity.Hipster.JeansType;

public class HipsterResourceTest {

	private static final String CONFIG_PATH = "src/main/resources/hipster.yml";

	@ClassRule
	public static final DropwizardAppRule<HipsterConfiguration> RULE = new DropwizardAppRule<HipsterConfiguration>(
			HipsterApplication.class, CONFIG_PATH);

	@Test
	public void test() {
		Client client = ClientBuilder.newClient();

		Hipster fooHipster = getHipster("foo");

		ClientResponse response = client
				.target(String.format("http://localhost:%d",
						RULE.getLocalPort())).path("hipsters")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(fooHipster), ClientResponse.class);

		assertEquals(201, response.getStatus());

		Hipster hipReceived = client
				.target(String.format("http://localhost:%d/hipsters/foo",
						RULE.getLocalPort()))
				.request(MediaType.APPLICATION_JSON).get(Hipster.class);

		assertEquals(fooHipster, hipReceived);

	}

	private Hipster getHipster(String name) {
		return new Hipster(1, name, JeansType.SKINNY, false, "");
	}

}
