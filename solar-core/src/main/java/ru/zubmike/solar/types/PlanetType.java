package ru.zubmike.solar.types;

import ru.zubmike.core.types.BasicDictItem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planet_types")
public class PlanetType extends BasicDictItem<Integer> {

}
