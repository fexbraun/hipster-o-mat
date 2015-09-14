package com.ax.demo;

import com.ax.demo.config.HipsterConfiguration;
import com.ax.demo.entity.Hipster;
import com.ax.demo.entity.Hipster.JeansType;
import com.ax.demo.health.HipsterServiceHealthCheck;
import com.ax.demo.resource.HipsterResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * The {@link Application} class is the main entry point of your Dropwizard
 * service.
 * <p>
 * Here we register and wire the our REST resources, health checks, manageable
 * objects and so on.
 */
public class HipsterApplication extends Application<HipsterConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HipsterApplication().run(args);
	}

	@Override
	public void initialize(final Bootstrap<HipsterConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/image-folder", "/hipster-images", "index.html", "images"));
		bootstrap.addBundle(new AssetsBundle("/apidocs", "/doc", "service.json", "js"));

		bootstrap.addBundle(new ViewBundle<HipsterConfiguration>());

		bootstrap.addBundle(new SwaggerBundle<HipsterConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HipsterConfiguration configuration) {
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

	@Override
	public void run(final HipsterConfiguration configuration, final Environment environment) throws Exception {

		System.out.println("Hello " + configuration.getConferenceName() + configuration.getYear());

		final HipsterStore store = new HipsterStore();
		HipsterResource resource = new HipsterResource(store);

		environment.jersey().register(resource);

		environment.lifecycle().manage(store);

		environment.healthChecks().register("hipsterHealth", new HipsterServiceHealthCheck(store));

		// add one demo hipster
		store.store(new Hipster(0, "Foo", JeansType.SKINNY, true, "../../hipster-images/foo.jpg"));
	}

}
