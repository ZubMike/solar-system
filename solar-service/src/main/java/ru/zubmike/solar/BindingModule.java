package ru.zubmike.solar;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import ru.zubmike.solar.dao.PlanetDao;
import ru.zubmike.solar.dao.PlanetTypeDao;
import ru.zubmike.solar.dao.SatelliteDao;
import ru.zubmike.solar.dao.db.PlanetDaoImpl;
import ru.zubmike.solar.dao.db.PlanetTypeDaoImpl;
import ru.zubmike.solar.dao.db.SatelliteDaoImpl;
import ru.zubmike.solar.logic.PlanetLogic;
import ru.zubmike.solar.logic.SatelliteLogic;
import ru.zubmike.solar.models.PlanetTypeModel;
import ru.zubmike.solar.resources.DictionaryResource;
import ru.zubmike.solar.resources.PlanetResource;
import ru.zubmike.solar.resources.SatelliteResource;

public class BindingModule extends AbstractModule {
	
	private final ResourceConfig resourceConfig;
	private final SessionFactory sessionFactory;
	
	public BindingModule(ResourceConfig resourceConfig, SessionFactory sessionFactory) {
		this.resourceConfig = resourceConfig;
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void configure() {
		bind(ResourceConfig.class).toInstance(resourceConfig);
		bind(SessionFactory.class).toInstance(sessionFactory);

		bind(PlanetDao.class).to(PlanetDaoImpl.class);
		bind(SatelliteDao.class).to(SatelliteDaoImpl.class);
		bind(PlanetTypeDao.class).to(PlanetTypeDaoImpl.class);

		bind(PlanetTypeModel.class).in(Singleton.class);

		bind(PlanetLogic.class).in(Singleton.class);
		bind(SatelliteLogic.class).in(Singleton.class);

		bind(ModelInitializer.class).in(Singleton.class);
		
		bind(SolarSystemLoggingFilter.class).asEagerSingleton();
		bind(SolarSystemExceptionMapper.class).asEagerSingleton();
		
		bind(PlanetResource.class).asEagerSingleton();
		bind(SatelliteResource.class).asEagerSingleton();
		bind(DictionaryResource.class).asEagerSingleton();
	}

}
