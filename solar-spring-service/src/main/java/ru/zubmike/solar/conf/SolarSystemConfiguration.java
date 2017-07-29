package ru.zubmike.solar.conf;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.zubmike.solar.ModelInitializer;
import ru.zubmike.solar.types.Planet;
import ru.zubmike.solar.types.PlanetType;
import ru.zubmike.solar.types.Satellite;
import ru.zubmike.solar.utils.HibernateFactory;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.zubmike.solar")
@EnableConfigurationProperties(SolarSystemProperties.class)
public class SolarSystemConfiguration extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemConfiguration.class);

	private final SolarSystemProperties solarSystemProperties;

	@Autowired
	private ModelInitializer modelInitializer;

	@Autowired
	public SolarSystemConfiguration(SolarSystemProperties solarSystemProperties) {
		this.solarSystemProperties = solarSystemProperties;
	}

	@Bean
	public SessionFactory getSessionFactory() {
		return HibernateFactory.createSessionFactory(solarSystemProperties.getDatabase(),
				Planet.class, Satellite.class, PlanetType.class);
	}

	@PostConstruct
	public void init() {
		modelInitializer.run();
		LOGGER.info("initialization has finished");
	}

}
