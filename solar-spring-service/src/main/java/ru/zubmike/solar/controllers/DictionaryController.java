package ru.zubmike.solar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.solar.logic.DictionaryLogic;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	private final DictionaryLogic dictionaryLogic;

	@Autowired
	public DictionaryController(DictionaryLogic dictionaryLogic) {
		this.dictionaryLogic = dictionaryLogic;
	}

	@GetMapping("/planet-types")
	public List<DictItem<Integer>> getPlanetTypes() {
		return dictionaryLogic.getPlanetTypes();
	}
}
