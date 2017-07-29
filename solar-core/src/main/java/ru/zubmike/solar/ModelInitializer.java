package ru.zubmike.solar;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zubmike.solar.dao.PlanetTypeDao;
import ru.zubmike.solar.models.PlanetTypeModel;

@Component
public class ModelInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelInitializer.class);

	private final PlanetTypeDao planetTypeDao;

	private final PlanetTypeModel planetTypeModel;

	@Inject
	@Autowired
	public ModelInitializer(PlanetTypeDao planetTypeDao, PlanetTypeModel planetTypeModel) {
		this.planetTypeDao = planetTypeDao;
		this.planetTypeModel = planetTypeModel;
	}

	public void run() {
		LOGGER.info("filling models");
		planetTypeModel.fill(planetTypeDao.getAll());
	}
}
