package com.ax.demo.entity;

import com.ax.demo.resource.HipsterResource;

/**
 * Used as return value of a {@link HipsterResource#pingPong()} method.
 */
public class Pong {

	public static final Pong PONG = new Pong();

	public String msg = "Pong";
}
