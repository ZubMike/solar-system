package ru.zubmike.solar.dto;

public class SatelliteEntry extends NewSatelliteEntry {

	private static final long serialVersionUID = 1L;
	
	private int planetId;

	public SatelliteEntry() {

	}

	public SatelliteEntry(String name, int planetId) {
		super(name);
		this.planetId = planetId;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}
}
