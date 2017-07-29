package ru.zubmike.solar.logic;

import com.google.inject.Inject;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.core.utils.DictItemUtils;
import ru.zubmike.solar.models.PlanetTypeModel;

import java.util.List;

public class DictionaryLogic {

	private PlanetTypeModel planetTypeModel;

	@Inject
	public DictionaryLogic(PlanetTypeModel planetTypeModel) {
		this.planetTypeModel = planetTypeModel;
	}

	public List<DictItem<Integer>> getPlanetTypes() {
		return DictItemUtils.createIntItems(planetTypeModel.getItems());
	}
}
