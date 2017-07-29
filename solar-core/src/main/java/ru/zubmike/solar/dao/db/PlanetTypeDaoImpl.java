package ru.zubmike.solar.dao.db;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;
import ru.zubmike.core.dao.db.BasicItemDao;
import ru.zubmike.solar.dao.PlanetTypeDao;
import ru.zubmike.solar.types.PlanetType;

public class PlanetTypeDaoImpl extends BasicItemDao<Integer, PlanetType> implements PlanetTypeDao {

	@Inject
	public PlanetTypeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, PlanetType.class);
	}
}
