package ru.zubmike.solar.resources;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import ru.zubmike.core.types.DictItem;
import ru.zubmike.solar.dto.PlanetEntry;
import ru.zubmike.solar.dto.PlanetInfo;
import ru.zubmike.solar.dto.SatelliteEntry;
import ru.zubmike.solar.dto.SatelliteInfo;
import ru.zubmike.solar.logic.PlanetLogic;
import ru.zubmike.solar.logic.SatelliteLogic;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/planets")
public class PlanetResource extends AbstractResource {

	private final PlanetLogic planetLogic;
	private final SatelliteLogic satelliteLogic;

	@Inject
	public PlanetResource(ResourceConfig resourceConfig, PlanetLogic planetLogic, SatelliteLogic satelliteLogic) {
		super(resourceConfig);
		this.planetLogic = planetLogic;
		this.satelliteLogic = satelliteLogic;
	}

	@POST
	public PlanetInfo addPlanet(PlanetEntry planetEntry) {
		return planetLogic.addPlanet(planetEntry);
	}

	@PUT
	@Path("/{id}")
	public PlanetInfo updatePlanet(@PathParam("id") int id, PlanetEntry planetEntry) {
		return planetLogic.updatePlanet(id, planetEntry);
	}

	@GET
	public List<DictItem<Integer>> getPlanets() {
		return planetLogic.getPlanets();
	}

	@GET
	@Path("/{id}")
	public PlanetInfo getPlanet(@PathParam("id") int id) {
		return planetLogic.getPlanet(id);
	}

	@DELETE
	@Path("/{id}")
	public Response removePlanet(@PathParam("id") int id) {
		planetLogic.removePlanet(id);
		return Response.ok().build();
	}

	@POST
	@Path("/{id}/satellites")
	public SatelliteInfo addSatellite(@PathParam("id") int planetId,
	                                  SatelliteEntry satelliteEntry) {
		return satelliteLogic.addSatellite(planetId, satelliteEntry);
	}

	@GET
	@Path("/{id}/satellites")
	public List<DictItem<Integer>> getSatellite(@PathParam("id") int planetId) {
		return satelliteLogic.getSatellites(planetId);
	}

	@DELETE
	@Path("/{id}/satellites")
	public Response removeSatellites(@PathParam("id") int planetId) {
		satelliteLogic.removeSatellites(planetId);
		return Response.ok().build();
	}
}
