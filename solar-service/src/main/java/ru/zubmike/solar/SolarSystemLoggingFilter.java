package ru.zubmike.solar;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

public class SolarSystemLoggingFilter implements ContainerResponseFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemLoggingFilter.class);

	@Context
	private HttpServletRequest httpServletRequest;

	@Inject
	public SolarSystemLoggingFilter(ResourceConfig resourceConfig) {
		resourceConfig.register(this);
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		LOGGER.info("{}\n{}\n{}",
				httpServletRequest.getRemoteAddr(),
				createRequestInfo(requestContext),
				createResponseInfo(responseContext));
	}

	private String createResponseInfo(ContainerResponseContext responseContext) {
		return "<- " + responseContext.getStatus();
	}

	private String createRequestInfo(ContainerRequestContext requestContext) {
		String resource = requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri();
		return "-> "+ resource;
	}

}
