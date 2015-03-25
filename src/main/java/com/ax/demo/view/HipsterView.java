package com.ax.demo.view;

import io.dropwizard.views.View;

import com.ax.demo.entity.Hipster;
import com.ax.demo.resource.HipsterResource;

/**
 * The Class HipsterView composes the entity instance and the mustache view of
 * the hipster. It is returned in {@link HipsterResource#getHipsterView(String)}
 * .
 */
public class HipsterView extends View {

	private final Hipster hipster;

	public HipsterView(Hipster hipster) {
		super("hipster.mustache");
		this.hipster = hipster;
	}

	public Hipster getHipster() {
		return hipster;
	}
}
