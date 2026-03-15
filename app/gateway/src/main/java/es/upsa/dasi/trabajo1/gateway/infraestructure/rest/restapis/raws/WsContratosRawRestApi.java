package es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws;

import es.upsa.dasi.trabajo1.domain.dtos.ContratoDto;
import es.upsa.dasi.trabajo1.gateway.adapters.input.rest.utils.HttpXHeaders;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8084")
public interface WsContratosRawRestApi
{
    @GET
    @Path("/contratos/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findByEquipoId(@PathParam("id") String id);

    @POST
    @Path("/contratos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(ContratoDto contratoDto,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PROTO) String schema,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_HOST) String host,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PORT) String port
                   );
    @GET
    @Path("/contratos/{id}")
    Response findById(@PathParam("id") String id);

    @DELETE
    @Path("/contratos/{id}")
    Response deleteById(@PathParam("id") String id);
}
