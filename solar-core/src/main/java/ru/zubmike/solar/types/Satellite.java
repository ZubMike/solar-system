package ru.zubmike.solar.types;

import ru.zubmike.core.types.BasicDictItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "satellites")
public class Satellite extends BasicDictItem<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "planet_id")
	private int planetId;

	public int getPlanetId() {
		return planetId;
	}
	
	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

}
