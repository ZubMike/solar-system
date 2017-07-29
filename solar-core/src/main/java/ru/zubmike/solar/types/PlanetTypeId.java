package ru.zubmike.solar.types;

import java.util.Arrays;
import java.util.Optional;

public enum PlanetTypeId {

	TERRESTRIAL(1),
	OUTER(2),
	DWARF(3);

	private int id;

	PlanetTypeId(int id) {
		this.id = id;
	}

	public int value() {
		return id;
	}

	public static Optional<PlanetTypeId> valueOf(int id) {
		return Arrays.stream(values())
				.filter(value -> value.id == id)
				.findAny();
	}
}
