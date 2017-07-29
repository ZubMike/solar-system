package ru.zubmike.solar.dao.db;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zubmike.core.dao.db.BasicEntityItemDao;
import ru.zubmike.solar.dao.PlanetDao;
import ru.zubmike.solar.types.Planet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class PlanetDaoImpl extends BasicEntityItemDao<Integer, Planet> implements PlanetDao {

	@Inject
	@Autowired
	public PlanetDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Planet.class);
	}

	@Override
	public Optional<Planet> getByNumber(int number) {
		return doReturning(session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Planet> query = builder.createQuery(clazz);
			Root<Planet> root = getRootAndSelect(query);
			query.where(builder.equal(root.get("number"), number));
			return session.createQuery(query).uniqueResultOptional();
		}, e -> createException(e, "can't get planet by number", String.valueOf(number)));
	}
}
