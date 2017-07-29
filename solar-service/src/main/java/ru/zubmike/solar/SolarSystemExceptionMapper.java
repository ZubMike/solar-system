package ru.zubmike.solar;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zubmike.solar.utils.NotFoundException;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SolarSystemExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemExceptionMapper.class);
	
	@Inject
	public SolarSystemExceptionMapper(ResourceConfig resourceConfig) {
		resourceConfig.register(this);
	}

	@Override
	public Response toResponse(Exception exception) {
		String message = exception.getMessage();
		if (exception instanceof IllegalArgumentException) {
			return createResponse(Response.Status.BAD_REQUEST, message);
		} else if (exception instanceof NotFoundException || exception instanceof javax.ws.rs.NotFoundException) {
			return createResponse(Response.Status.NOT_FOUND, "Not Found");
		} else if (exception instanceof NotAllowedException) {
			return createResponse(Response.Status.METHOD_NOT_ALLOWED, "Method Not Allowed");
		} else if (exception instanceof NotSupportedException) {
			return createResponse(Response.Status.UNSUPPORTED_MEDIA_TYPE, "Unsupported current content type");
		} else {
			LOGGER.error(message, exception);
			return createResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal service error");
		}
	}

	private static Response createResponse(Response.Status status, String message) {
		return Response.status(status.getStatusCode()).entity(message).type(MediaType.TEXT_PLAIN).build();
	}

}
