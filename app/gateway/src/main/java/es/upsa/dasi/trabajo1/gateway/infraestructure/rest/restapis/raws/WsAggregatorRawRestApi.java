package es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8086")
@Path("/contratos")
public interface WsAggregatorRawRestApi {
    @Path("/equipos/{id}")
    @GET
    @Produces
    Response findEquipoWithContratosById(@PathParam("id") String id);
}
