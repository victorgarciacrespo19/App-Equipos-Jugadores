package es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws;

import es.upsa.dasi.trabajo1.domain.dtos.JugadorDto;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.gateway.adapters.input.rest.utils.HttpXHeaders;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8083")
@Path("/jugadores")
public interface WsJugadoresRawRestApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(JugadorDto jugadorDto,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PROTO) String schema,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_HOST) String host,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PORT) String port
    );

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response update(@PathParam("id") String id, JugadorDto jugadorDto);

    @DELETE
    @Path("/{id}")
    Response deleteById(@PathParam("id") String id);
}
