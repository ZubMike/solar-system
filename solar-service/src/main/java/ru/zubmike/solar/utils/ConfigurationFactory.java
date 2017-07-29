package ru.zubmike.solar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.InternalServerErrorException;
import java.io.File;

public class ConfigurationFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);

	public static <T> T create(String configurationFile, Class<T> clazz) {
		return parse(configurationFile, clazz);
	}

	private static <T> T parse(String configurationFile, Class<T> clazz) {
		try {
			LOGGER.info("configuration: {}", configurationFile);
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			return mapper.readValue(new File(configurationFile), clazz);
		} catch (Exception e) {
			throw new InternalServerErrorException(e);
		}
	}

}
