package ru.zubmike.solar.dao;

import ru.zubmike.core.dao.EntityItemDao;
import ru.zubmike.solar.types.Satellite;

import java.util.List;

public interface SatelliteDao extends EntityItemDao<Integer, Satellite> {

    List<Satellite> getAllByPlanet(int planetId);
    void removeAllByPlanet(int planetId);

}
