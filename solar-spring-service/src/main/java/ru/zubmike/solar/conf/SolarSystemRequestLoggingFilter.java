package ru.zubmike.solar.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@Component
public class SolarSystemRequestLoggingFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemRequestLoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} finally {
			LOGGER.info("{}\n{}\n{}",
					request.getRemoteAddr(),
					createRequestInfo(request),
					createResponseInfo(response));
		}
	}

	private static String createResponseInfo(HttpServletResponse response) {
		return "<- " + response.getStatus();
	}

	private static String createRequestInfo(HttpServletRequest request) {
		return "-> " + request.getMethod() + " " + request.getRequestURI();
	}

}
