package ru.zubmike.solar.dao.db;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zubmike.core.dao.db.BasicEntityItemDao;
import ru.zubmike.solar.dao.SatelliteDao;
import ru.zubmike.solar.types.Satellite;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class SatelliteDaoImpl extends BasicEntityItemDao<Integer, Satellite> implements SatelliteDao {

	@Inject
	@Autowired
	public SatelliteDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Satellite.class);
	}

	@Override
	public List<Satellite> getAllByPlanet(int planetId) {
		return doReturning(session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Satellite> query = builder.createQuery(clazz);
			Root<Satellite> root = getRootAndSelect(query);
			query.where(builder.equal(root.get("planetId"), planetId));
			return session.createQuery(query).list();
		}, e -> createException(e, "can't get satellite by planet", String.valueOf(planetId)));
	}

	@Override
	public void removeAllByPlanet(int planetId) {
		doVoidTransaction(session ->{
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<Satellite> query = builder.createCriteriaDelete(clazz);
			Root<Satellite> root = query.from(clazz);
			query.where(builder.equal(root.get("planetId"), planetId));
			session.createQuery(query).executeUpdate();
		}, e -> createException(e, "can't remove satellites by planet", String.valueOf(planetId)));
	}
}
