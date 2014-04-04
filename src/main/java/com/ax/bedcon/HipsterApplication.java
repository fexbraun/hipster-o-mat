package com.ax.bedcon;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.ax.bedcon.config.HipsterConfiguration;

public class HipsterApplication extends Application<HipsterConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HipsterApplication().run(args);
	}

	@Override
	public void initialize(final Bootstrap<HipsterConfiguration> bootstrap) {
	}

	@Override
	public void run(final HipsterConfiguration configuration,
			final Environment environment) throws Exception {
	}

}
