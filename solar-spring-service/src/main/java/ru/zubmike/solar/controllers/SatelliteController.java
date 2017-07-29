package ru.zubmike.solar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zubmike.solar.dto.SatelliteEntry;
import ru.zubmike.solar.dto.SatelliteInfo;
import ru.zubmike.solar.logic.SatelliteLogic;

import java.util.List;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {

	private final SatelliteLogic satelliteLogic;

	@Autowired
	public SatelliteController(SatelliteLogic satelliteLogic) {
		this.satelliteLogic = satelliteLogic;
	}

	@PutMapping("/{id}")
	public SatelliteInfo updateSatellite(@PathVariable("id") int id, SatelliteEntry satelliteEntry) {
		return satelliteLogic.updateSatellite(id, satelliteEntry);
	}

	@GetMapping
	public List<SatelliteInfo> getSatellites() {
		return satelliteLogic.getSatellites();
	}

	@GetMapping("/{id}")
	public SatelliteInfo getSatellite(@PathVariable("id") int id) {
		return satelliteLogic.getSatellite(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeSatellite(@PathVariable("id") int id) {
		satelliteLogic.removeSatellite(id);
	}

}
