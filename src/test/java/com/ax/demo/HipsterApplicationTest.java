package com.ax.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Environment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ax.demo.config.HipsterConfiguration;
import com.ax.demo.health.HipsterServiceHealthCheck;
import com.ax.demo.resource.HipsterResource;
import com.codahale.metrics.health.HealthCheckRegistry;

public class HipsterApplicationTest {
	private final Environment environment = mock(Environment.class);
	private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
	private final HipsterApplication application = new HipsterApplication();
	private final HipsterConfiguration config = new HipsterConfiguration();

	private final LifecycleEnvironment lifecycle = mock(LifecycleEnvironment.class);
	private final HealthCheckRegistry healthcheckReg = mock(HealthCheckRegistry.class);

	@Before
	public void setup() throws Exception {
		config.setConferenceName("yay");
		when(environment.jersey()).thenReturn(jersey);
		when(environment.lifecycle()).thenReturn(lifecycle);
		when(environment.healthChecks()).thenReturn(healthcheckReg);
	}

	@Test
	public void buildsAHipsterResource() throws Exception {
		application.run(config, environment);

		verify(jersey).register(Mockito.isA(HipsterResource.class));
		verify(lifecycle).manage(Mockito.isA(HipsterStore.class));
		verify(healthcheckReg).register(Mockito.anyString(),
				Mockito.isA(HipsterServiceHealthCheck.class));

	}
}
