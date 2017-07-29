package ru.zubmike.solar.dto;

import ru.zubmike.core.types.DictItem;

import java.util.List;

public class PlanetInfo extends PlanetEntry implements DictItem<Integer> {

	private static final long serialVersionUID = 1L;

	private int id;
	private String typeName;
	private List<DictItem<Integer>> satellites;
	
	public PlanetInfo() {
	}
	
	public PlanetInfo(int id, String name, int number, int typeId, String typeName, Integer radius,
	                  List<DictItem<Integer>> satellites) {
		super(name, number, typeId, radius);
		this.id = id;
		this.typeName = typeName;
		this.satellites = satellites;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<DictItem<Integer>> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<DictItem<Integer>> satellites) {
		this.satellites = satellites;
	}

}
