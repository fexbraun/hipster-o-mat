package com.ax.bedcon;

import io.dropwizard.lifecycle.Managed;

import java.util.HashMap;

import com.ax.bedcon.entity.Hipster;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class HipsterStore implements Managed {

	final HashMap<String, Hipster> internalStore = Maps.newHashMap();
	private boolean running = true;

	public void store(final Hipster hipster) {
		internalStore.put(hipster.getName(), hipster);
	}

	public Optional<Hipster> get(final String name) {
		return Optional.fromNullable(internalStore.get(name));
	}

	@Override
	public void start() throws Exception {
		running = true;
	}

	@Override
	public void stop() throws Exception {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}
}
