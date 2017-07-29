package ru.zubmike.solar;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ru.zubmike.solar.conf.SolarSystemConfiguration;

import java.util.Properties;

@SpringBootApplication(
		scanBasePackageClasses = SolarSystemConfiguration.class,
		exclude = ErrorMvcAutoConfiguration.class)
public class SolarSystemApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SolarSystemApplication.class)
				.properties(createProperties())
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

	private static Properties createProperties() {
		Properties properties = new Properties();
		properties.put("spring.mvc.throw-exception-if-no-handler-found", true);
		return properties;
	}

}
