package ru.zubmike.solar.logic;

import com.google.inject.Inject;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.core.utils.DictItemUtils;
import ru.zubmike.solar.dao.PlanetDao;
import ru.zubmike.solar.dao.SatelliteDao;
import ru.zubmike.solar.dto.NewSatelliteEntry;
import ru.zubmike.solar.dto.SatelliteEntry;
import ru.zubmike.solar.dto.SatelliteInfo;
import ru.zubmike.solar.types.Planet;
import ru.zubmike.solar.types.Satellite;
import ru.zubmike.solar.utils.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class SatelliteLogic {
	
	private final SatelliteDao satelliteDao;
	private final PlanetDao planetDao;
	
	@Inject
	public SatelliteLogic(SatelliteDao satelliteDao, PlanetDao planetDao) {
		this.satelliteDao = satelliteDao;
		this.planetDao = planetDao;
	}

	public SatelliteInfo addSatellite(int planetId, NewSatelliteEntry entry) {
		checkSatelliteData(planetId);
		Satellite item = new Satellite();
		item.setName(entry.getName());
		item.setPlanetId(planetId);
		satelliteDao.add(item);
		return createSatelliteInfo(item);
	}

	private void checkSatelliteData(int planetId) {
		planetDao.get(planetId).orElseThrow(NotFoundException::new);
	}

	public SatelliteInfo updateSatellite(int id, SatelliteEntry entry) {
		checkSatelliteData(entry.getPlanetId());
		Satellite item = satelliteDao.get(id).orElseThrow(NotFoundException::new);
		updateSatelliteData(item, entry);
		satelliteDao.update(item);
		return createSatelliteInfo(item);
	}

	private static void updateSatelliteData(Satellite item, SatelliteEntry entry) {
		item.setName(entry.getName());
		item.setPlanetId(entry.getPlanetId());
	}

	public SatelliteInfo getSatellite(int id) {
		Satellite item = satelliteDao.get(id).orElseThrow(NotFoundException::new);
		return createSatelliteInfo(item);
	}

	public List<SatelliteInfo> getSatellites() {
		return satelliteDao.getAll().stream()
				.map(this::createSatelliteInfo)
				.collect(Collectors.toList());
	}

	public List<DictItem<Integer>> getSatellites(int planetId) {
		List<Satellite> items = satelliteDao.getAllByPlanet(planetId);
		return DictItemUtils.createIntItems(items);
	}

	private SatelliteInfo createSatelliteInfo(Satellite item) {
		return new SatelliteInfo(item.getId(),
				item.getName(),
				item.getPlanetId(),
				planetDao.get(item.getPlanetId()).map(Planet::getName).orElse(null));
	}

	public void removeSatellite(int id) {
		Satellite item = satelliteDao.get(id).orElseThrow(NotFoundException::new);
		satelliteDao.remove(item);
	}
	
	public void removeSatellites(int planetId) {
		satelliteDao.removeAllByPlanet(planetId);
	}

}
