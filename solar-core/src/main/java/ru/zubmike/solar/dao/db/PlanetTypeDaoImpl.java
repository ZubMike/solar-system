package ru.zubmike.solar.dao.db;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zubmike.core.dao.db.BasicItemDao;
import ru.zubmike.solar.dao.PlanetTypeDao;
import ru.zubmike.solar.types.PlanetType;

@Component
public class PlanetTypeDaoImpl extends BasicItemDao<Integer, PlanetType> implements PlanetTypeDao {

	@Inject
	@Autowired
	public PlanetTypeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, PlanetType.class);
	}
}
