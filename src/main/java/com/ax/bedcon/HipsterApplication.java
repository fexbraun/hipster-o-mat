package com.ax.bedcon;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import com.ax.bedcon.config.HipsterConfiguration;
import com.ax.bedcon.health.HipsterServiceHealthCheck;
import com.ax.bedcon.resource.HipsterResource;

public class HipsterApplication extends Application<HipsterConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HipsterApplication().run(args);
	}

	@Override
	public void initialize(final Bootstrap<HipsterConfiguration> bootstrap) {

		bootstrap.addBundle(new ViewBundle());

		bootstrap.addBundle(new AssetsBundle("/image-folder",
				"/hipsters-images"));
	}

	@Override
	public void run(final HipsterConfiguration configuration,
			final Environment environment) throws Exception {
		System.out.println("Hello " + configuration.getConferenceName()
				+ configuration.getYear());

		final HipsterStore store = new HipsterStore();
		HipsterResource resource = new HipsterResource(store);

		environment.lifecycle().manage(store);
		environment.jersey().register(resource);

		environment.healthChecks().register("hipHealth",
				new HipsterServiceHealthCheck(store));
	}

}
