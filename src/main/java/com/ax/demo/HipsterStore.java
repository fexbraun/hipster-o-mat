package com.ax.demo;

import java.util.Collection;
import java.util.HashMap;

import com.ax.demo.entity.Hipster;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import io.dropwizard.lifecycle.Managed;

/**
 * The Class HipsterStore is a mem-only storage for {@link Hipster} entities. It
 * simulates a persistent storage for this demo project.
 */
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

	public Collection<Hipster> getAll() {
		return internalStore.values();
	}
}
