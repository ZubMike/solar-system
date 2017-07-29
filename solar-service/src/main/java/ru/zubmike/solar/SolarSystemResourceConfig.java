package ru.zubmike.solar;

import com.google.inject.Guice;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zubmike.solar.types.Planet;
import ru.zubmike.solar.types.PlanetType;
import ru.zubmike.solar.types.Satellite;
import ru.zubmike.solar.utils.ConfigurationFactory;
import ru.zubmike.solar.utils.DataBaseConfiguration;
import ru.zubmike.solar.utils.HibernateFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class SolarSystemResourceConfig extends ResourceConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemResourceConfig.class);
	private static final String CONFIGURATION_FILE = "configuration.yml";
	
	public SolarSystemResourceConfig() {
		init();
	}
	
	public static void main(String[] args) {
		try {
			new SolarSystemResourceConfig();
		} catch (Exception e) {
			LOGGER.error("Exception when start service", e);
		}
	}

	private void init() {
		LOGGER.info("binding modules");
		Guice.createInjector(new BindingModule(this, createSessionFactory()))
				.getInstance(ModelInitializer.class).run();
		LOGGER.info("initialization has finished");
	}

	private SessionFactory createSessionFactory() {
		DataBaseConfiguration dataBaseConfiguration = ConfigurationFactory.create(CONFIGURATION_FILE, DataBaseConfiguration.class);
		return HibernateFactory.createSessionFactory(dataBaseConfiguration,
				Planet.class, Satellite.class, PlanetType.class);
	}

}
