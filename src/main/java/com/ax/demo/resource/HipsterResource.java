package com.ax.demo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.ax.demo.HipsterStore;
import com.ax.demo.entity.Hipster;
import com.ax.demo.view.HipsterView;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.Uninterruptibles;

@Path("/hipsters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HipsterResource {
	private final HipsterStore store;

	public HipsterResource(final HipsterStore store) {
		this.store = store;
	}

	@POST
	public Response addHipster(final Hipster hipster,
			@Context final UriInfo uriInfo) throws URISyntaxException {

		String imgPath = uriInfo.getBaseUriBuilder().path("hipster-images")
				.path(hipster.getName() + ".jpg").build().toString();

		hipster.setImagePath(imgPath);

		store.store(hipster);
		return Response.created(new URI("/" + hipster.getName())).build();
	}

	@GET
	@Timed
	@Path("{name}")
	public Hipster getHipster(@PathParam("name") final String name) {

		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

		final Optional<Hipster> hipster = store.get(name);
		if (hipster.isPresent()) {
			return hipster.get();
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

	@GET
	@Path("{name}/view")
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public HipsterView getHipsterView(@PathParam("name") String name) {
		return new HipsterView(getHipster(name));
	}
}
