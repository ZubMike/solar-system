package ru.zubmike.solar.dto;

import java.io.Serializable;

public class PlanetEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int number;
	private int typeId;
	private Integer radius;

	public PlanetEntry() {
	}

	public PlanetEntry(String name, int number, int typeId, Integer radius) {
		this.name = name;
		this.number = number;
		this.typeId = typeId;
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}
}
