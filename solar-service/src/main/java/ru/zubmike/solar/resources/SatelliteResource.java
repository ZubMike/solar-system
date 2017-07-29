package ru.zubmike.solar.resources;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import ru.zubmike.solar.dto.SatelliteEntry;
import ru.zubmike.solar.dto.SatelliteInfo;
import ru.zubmike.solar.logic.SatelliteLogic;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/satellites")
public class SatelliteResource extends AbstractResource {

	private final SatelliteLogic satelliteLogic;

	@Inject
	public SatelliteResource(ResourceConfig resourceConfig, SatelliteLogic satelliteLogic) {
		super(resourceConfig);
		this.satelliteLogic = satelliteLogic;
	}

	@PUT
	@Path("/{id}")
	public Response updateSatellite(@PathParam("id") int id, SatelliteEntry satelliteEntry) {
		satelliteLogic.updateSatellite(id, satelliteEntry);
		return Response.ok().build();
	}

	@GET
	public List<SatelliteInfo> getSatellites() {
		return satelliteLogic.getSatellites();
	}

	@GET
	@Path("/{id}")
	public SatelliteInfo getSatellite(@PathParam("id") int id) {
		return satelliteLogic.getSatellite(id);
	}

	@DELETE
	@Path("/{id}")
	public Response removeSatellite(@PathParam("id") int id) {
		satelliteLogic.removeSatellite(id);
		return Response.ok().build();
	}
}
