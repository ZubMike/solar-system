package ru.zubmike.solar.logic;

import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zubmike.core.types.BasicDictItem;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.core.utils.DictItemUtils;
import ru.zubmike.core.utils.DuplicateException;
import ru.zubmike.solar.dao.PlanetDao;
import ru.zubmike.solar.dto.PlanetEntry;
import ru.zubmike.solar.dto.PlanetInfo;
import ru.zubmike.solar.models.PlanetTypeModel;
import ru.zubmike.solar.types.Planet;
import ru.zubmike.solar.types.PlanetTypeId;
import ru.zubmike.solar.types.Satellite;
import ru.zubmike.solar.utils.NotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlanetLogic {

	private static final int MIN_RADIUS = 200;
	private static final int MAX_DWARF_RADIUS = 600;
	private static final int MAX_RADIUS = 70000;

	private final PlanetDao planetDao;

	private final PlanetTypeModel planetTypeModel;
	
	@Inject
	@Autowired
	public PlanetLogic(PlanetDao planetDao, PlanetTypeModel planetTypeModel) {
		this.planetDao = planetDao;
		this.planetTypeModel = planetTypeModel;
	}
	
	public List<DictItem<Integer>> getPlanets() {
		return planetDao.getAll().stream()
				.map(item -> new BasicDictItem<>(item.getId(), item.getName()))
				.collect(Collectors.toList());
	}
	
	public PlanetInfo addPlanet(PlanetEntry entry) {
		checkPlanetData(entry);
		checkDuplicate(entry);
		Planet item = new Planet();
		updatePlanetData(item, entry);
		planetDao.add(item);
		return createPlanetInfo(item, Collections.emptyList());
	}

	private void checkDuplicate(PlanetEntry entry) {
		Optional<Planet> duplicate = planetDao.getByNumber(entry.getNumber());
		if (duplicate.isPresent()) {
			throw new DuplicateException("Planet number has already exists");
		}
	}

	private static void checkPlanetData(PlanetEntry entry) {
		PlanetTypeId planetTypeId = PlanetTypeId.valueOf(entry.getTypeId())
				.orElseThrow(() -> new IllegalArgumentException("Planet type is invalid"));
		Integer radius = entry.getRadius();
		if (radius != null) {
			if (radius < MIN_RADIUS || radius > MAX_RADIUS) {
				throw new IllegalArgumentException("Planet radius is invalid");
			}
			if (planetTypeId == PlanetTypeId.DWARF && radius > MAX_DWARF_RADIUS) {
				throw new IllegalArgumentException("Dwarf planet radius is invalid");
			}
		}
		if (entry.getNumber() <= 0) {
			throw new IllegalArgumentException("Planet number is invalid");
		}
	}

	public PlanetInfo updatePlanet(int id, PlanetEntry entry) {
		checkPlanetData(entry);
		Planet item = planetDao.get(id).orElseThrow(NotFoundException::new);
		updatePlanetData(item, entry);
		planetDao.update(item);
		return createPlanetInfo(item, item.getSatellites());
	}

	private static void updatePlanetData(Planet item, PlanetEntry entry) {
		item.setNumber(entry.getNumber());
		item.setName(entry.getName());
		item.setTypeId(entry.getTypeId());
		item.setRadius(entry.getRadius());
	}

	public PlanetInfo getPlanet(int id) {
		Planet item = planetDao.get(id).orElseThrow(NotFoundException::new);
		return createPlanetInfo(item, item.getSatellites());
	}

	private PlanetInfo createPlanetInfo(Planet item, Collection<Satellite> satellites) {
		return new PlanetInfo(item.getId(),
				item.getName(),
				item.getNumber(),
				item.getTypeId(),
				planetTypeModel.getName(item.getTypeId()),
				item.getRadius(),
				DictItemUtils.createIntItems(satellites));
	}

	public void removePlanet(int id) {
		Planet item = planetDao.get(id).orElseThrow(NotFoundException::new);
		planetDao.remove(item);
	}
	
}