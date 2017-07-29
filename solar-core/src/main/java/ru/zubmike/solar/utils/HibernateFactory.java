package ru.zubmike.solar.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class HibernateFactory {
	
	public static SessionFactory createSessionFactory(DataBaseConfiguration dataBaseConfiguration, Class<?>... entities) {
		Configuration hibernateConfiguration = createConfiguration(dataBaseConfiguration, entities);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(hibernateConfiguration.getProperties());
		return hibernateConfiguration.buildSessionFactory(builder.build());
	}
	
	private static Configuration createConfiguration(DataBaseConfiguration dataBaseConfiguration, Class<?>... entities) {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.driver_class", dataBaseConfiguration.getDriverClass());
		configuration.setProperty("hibernate.dialect", dataBaseConfiguration.getDialect());
		configuration.setProperty("hibernate.connection.url", dataBaseConfiguration.getUrl());
		configuration.setProperty("hibernate.connection.username", dataBaseConfiguration.getUser());
		configuration.setProperty("hibernate.connection.password", dataBaseConfiguration.getPassword());

		Arrays.asList(entities).forEach(configuration::addAnnotatedClass);

		return configuration;
	}

}
