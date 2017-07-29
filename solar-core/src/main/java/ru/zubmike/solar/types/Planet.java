package ru.zubmike.solar.types;

import ru.zubmike.core.types.BasicDictItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "planets")
public class Planet extends BasicDictItem<Integer> {

	private static final long serialVersionUID = 1L;

	@Column(name = "number")
	private int number;

	@Column(name = "type_id")
	private int typeId;

	@Column(name = "radius")
	private Integer radius;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="planet_id")
	private List<Satellite> satellites;

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
		
	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}

}
