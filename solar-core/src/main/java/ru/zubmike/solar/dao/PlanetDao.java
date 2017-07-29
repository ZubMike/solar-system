package ru.zubmike.solar.dao;

import ru.zubmike.core.dao.EntityItemDao;
import ru.zubmike.solar.types.Planet;

import java.util.Optional;

public interface PlanetDao extends EntityItemDao<Integer, Planet> {

	Optional<Planet> getByNumber(int number);

}
