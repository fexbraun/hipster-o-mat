package com.ax.demo.health;

import com.ax.demo.HipsterStore;
import com.codahale.metrics.health.HealthCheck;

public class HipsterServiceHealthCheck extends HealthCheck {

	private final HipsterStore store;

	public HipsterServiceHealthCheck(final HipsterStore store) {
		this.store = store;
	}

	@Override
	protected Result check() throws Exception {
		if (store.isRunning()) {
			return Result.healthy("I'm fine. Store is running.");
		} else {
			return Result.unhealthy("Oh no, no storage for the hipsters.");
		}
	}

}
