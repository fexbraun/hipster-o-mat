package com.ax.demo.resource;

import static org.junit.Assert.assertEquals;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;

import com.ax.demo.HipsterApplication;
import com.ax.demo.config.HipsterConfiguration;
import com.ax.demo.entity.Hipster;
import com.ax.demo.entity.Hipster.JeansType;

public class HipsterIntegrationTest {

	@ClassRule
	public static final DropwizardAppRule<HipsterConfiguration> RULE = new DropwizardAppRule<HipsterConfiguration>(
			HipsterApplication.class,
			ResourceHelpers.resourceFilePath("hipster.yml"));

	@Test
	public void testHipsterGetCreateRoundtrip() {
		String hipName = "Gerard";
		Hipster fooHipster = getHipster(hipName);

		Client client = ClientBuilder.newClient();
		Response response = client
				.target(String.format("http://localhost:%d",
						RULE.getLocalPort())).path("hipsters")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(fooHipster));

		assertEquals(201, response.getStatus());

		Hipster hipReceived = client
				.target(String.format(
						"http://localhost:%d/hipsters/" + hipName,
						RULE.getLocalPort()))
				.request(MediaType.APPLICATION_JSON).get(Hipster.class);

		assertEquals(fooHipster, hipReceived);

	}

	private Hipster getHipster(String name) {
		return new Hipster(1, name, JeansType.SKINNY, true,
				"http://localhost:12345/hipster-images/" + name + ".jpg");
	}

}
