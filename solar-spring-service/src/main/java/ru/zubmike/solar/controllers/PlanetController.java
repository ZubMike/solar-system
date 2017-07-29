package ru.zubmike.solar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.solar.dto.PlanetEntry;
import ru.zubmike.solar.dto.PlanetInfo;
import ru.zubmike.solar.dto.SatelliteEntry;
import ru.zubmike.solar.dto.SatelliteInfo;
import ru.zubmike.solar.logic.PlanetLogic;
import ru.zubmike.solar.logic.SatelliteLogic;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	private final PlanetLogic planetLogic;
	private final SatelliteLogic satelliteLogic;

	@Autowired
	public PlanetController(PlanetLogic planetLogic, SatelliteLogic satelliteLogic) {
		this.planetLogic = planetLogic;
		this.satelliteLogic = satelliteLogic;
	}

	@PostMapping
	public PlanetInfo addPlanet(@RequestBody PlanetEntry planetEntry) {
		return planetLogic.addPlanet(planetEntry);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePlanet(@PathVariable("id") int id, @RequestBody PlanetEntry planetEntry) {
		planetLogic.updatePlanet(id, planetEntry);
	}

	@GetMapping()
	public List<DictItem<Integer>> getPlanets() {
		return planetLogic.getPlanets();
	}

	@GetMapping("/{id}")
	public PlanetInfo getPlanet(@PathVariable("id") int id) {
		return planetLogic.getPlanet(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removePlanet(@PathVariable("id") int id) {
		planetLogic.removePlanet(id);
	}

	@PostMapping("/{id}/satellites")
	public SatelliteInfo addSatellite(@PathVariable("id") int planetId,
	                                  @RequestBody SatelliteEntry satelliteEntry) {
		return satelliteLogic.addSatellite(planetId, satelliteEntry);
	}

	@GetMapping("/{id}/satellites")
	public List<DictItem<Integer>> getSatellite(@PathVariable("id") int planetId) {
		return satelliteLogic.getSatellites(planetId);
	}

	@DeleteMapping("/{id}/satellites")
	@ResponseStatus(HttpStatus.OK)
	public void removeSatellites(@PathVariable("id") int planetId) {
		satelliteLogic.removeSatellites(planetId);
	}
}
