package com.ax.bedcon;

import java.util.HashMap;

import com.ax.bedcon.entity.Hipster;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class HipsterStore {

	final HashMap<String, Hipster> internalStore = Maps.newHashMap();
	private final boolean running = true;

	public void store(final Hipster hipster) {
		internalStore.put(hipster.getName(), hipster);
	}

	public Optional<Hipster> get(final String name) {
		return Optional.fromNullable(internalStore.get(name));
	}

	public boolean isRunning() {
		return running;
	}
}
