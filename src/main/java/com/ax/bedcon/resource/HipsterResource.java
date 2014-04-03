package com.ax.bedcon.resource;

import java.net.URI;
import java.net.URISyntaxException;

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

import com.ax.bedcon.HipsterStore;
import com.ax.bedcon.entity.Hipster;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

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
		hipster.setImagePath(uriInfo.getAbsolutePath().toString() //
				+ "-images/" + hipster.getName() + ".jpg");

		store.store(hipster);
		return Response.created(new URI("/" + hipster.getName())).build();
	}

	@GET
	@Timed
	@Path("{name}")
	public Hipster getHipster(@PathParam("name") final String name,
			@Context final UriInfo uriInfo) {
		final Optional<Hipster> hipster = store.get(name);
		if (hipster.isPresent()) {
			return hipster.get();
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
}
