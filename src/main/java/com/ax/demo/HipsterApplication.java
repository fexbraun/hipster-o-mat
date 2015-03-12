package com.ax.demo;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import com.ax.demo.config.HipsterConfiguration;
import com.ax.demo.health.HipsterServiceHealthCheck;
import com.ax.demo.resource.HipsterResource;
import com.google.common.collect.ImmutableMap;

public class HipsterApplication extends Application<HipsterConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HipsterApplication().run(args);
	}

	@Override
	public void initialize(final Bootstrap<HipsterConfiguration> bootstrap) {
		bootstrap
				.addBundle(new AssetsBundle("/image-folder", "/hipster-images"));

		bootstrap.addBundle(new ViewBundle<HipsterConfiguration>() {
			@Override
			public ImmutableMap<String, ImmutableMap<String, String>> getViewConfiguration(
					HipsterConfiguration config) {
				return config.getViewRendererConfiguration();
			}
		});
	}

	@Override
	public void run(final HipsterConfiguration configuration,
			final Environment environment) throws Exception {

		System.out.println("Hello " + configuration.getConferenceName()
				+ configuration.getYear());

		final HipsterStore store = new HipsterStore();
		HipsterResource resource = new HipsterResource(store);

		environment.jersey().register(resource);

		environment.lifecycle().manage(store);

		environment.healthChecks().register("hipsterHealth",
				new HipsterServiceHealthCheck(store));
	}

}
