package ru.zubmike.solar.dto;

import java.io.Serializable;

public class NewSatelliteEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public NewSatelliteEntry() {

	}

	public NewSatelliteEntry(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
