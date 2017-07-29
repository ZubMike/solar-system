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

	@POST
	@Path("/{satellite_id}")
	public Response updateSatellite(@PathParam("satellite_id") int id, SatelliteEntry satelliteEntry) {
		satelliteLogic.updateSatellite(id, satelliteEntry);
		return Response.ok().build();
	}

	@GET
	public List<SatelliteInfo> getSatellites() {
		return satelliteLogic.getSatellites();
	}

	@GET
	@Path("/{satellite_id}")
	public SatelliteInfo getSatellite(@PathParam("satellite_id") int id) {
		return satelliteLogic.getSatellite(id);
	}

	@DELETE
	@Path("/{satellite_id}")
	public Response removeSatellite(@PathParam("satellite_id") int id) {
		satelliteLogic.removeSatellite(id);
		return Response.ok().build();
	}
}
