package ru.zubmike.solar.resources;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.solar.logic.DictionaryLogic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/dictionary")
public class DictionaryResource extends AbstractResource {

	private final DictionaryLogic dictionaryLogic;

	@Inject
	public DictionaryResource(ResourceConfig resourceConfig, DictionaryLogic dictionaryLogic) {
		super(resourceConfig);
		this.dictionaryLogic = dictionaryLogic;
	}

	@GET
	@Path("/planet-types")
	public List<DictItem<Integer>> getPlanetTypes() {
		return dictionaryLogic.getPlanetTypes();
	}

}
