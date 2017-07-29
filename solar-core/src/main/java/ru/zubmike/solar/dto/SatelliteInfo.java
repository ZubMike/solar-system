package ru.zubmike.solar.dto;

import ru.zubmike.core.types.DictItem;

public class SatelliteInfo extends SatelliteEntry implements DictItem<Integer> {

    private static final long serialVersionUID = 1L;

    private int id;
    private String planetName;

    public SatelliteInfo() {

    }

    public SatelliteInfo(int id, String name, int planetId, String planetName) {
        super(name, planetId);
        this.id = id;
        this.planetName = planetName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}
