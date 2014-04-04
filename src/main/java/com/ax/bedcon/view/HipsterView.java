package com.ax.bedcon.view;

import io.dropwizard.views.View;

import com.ax.bedcon.entity.Hipster;

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
