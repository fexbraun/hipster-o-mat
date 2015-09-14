package com.ax.demo.entity;

import java.util.Objects;

import com.ax.demo.resource.HipsterResource;

/**
 * Used as return value of a {@link HipsterResource#pingPong()} method.
 */
public class Pong {

	public static final Pong PONG = new Pong();

	public String msg = "Pong";

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return obj!=null && obj instanceof Pong && //
				Objects.equals(msg, ((Pong)obj).msg);
	}
	
	
}
