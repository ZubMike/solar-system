package ru.zubmike.solar.resources;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AbstractResource {

	public AbstractResource(ResourceConfig resourceConfig) {
		resourceConfig.register(this);
	}

}

