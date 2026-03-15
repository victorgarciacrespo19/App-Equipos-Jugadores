package es.upsa.dasi.trabajo1.gateway.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.dtos.JugadorDto;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws.WsJugadoresRawRestApi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;

@Path("/jugadores")
public class JugadoresResource
{
    @Inject
    @RestClient
    WsJugadoresRawRestApi wsJugadoresRawRestApi;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJugadores() throws EquiposAppException
    {
        return wsJugadoresRawRestApi.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJugadorById(@PathParam("id") String id) throws EquiposAppException
    {
        return wsJugadoresRawRestApi.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JugadorDto jugadorDto, @Context UriInfo uriInfo) throws EquiposAppException
    {
        URI baseUri = uriInfo.getBaseUri();
        String scheme = baseUri.getScheme();
        String host = baseUri.getHost();
        String port = String.valueOf(baseUri.getPort());

        return wsJugadoresRawRestApi.create(jugadorDto, scheme, host, port);

    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(JugadorDto jugadorDto, @PathParam("id") String id) throws EquiposAppException
    {
        return wsJugadoresRawRestApi.update(id, jugadorDto);
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") String id) throws EquiposAppException
    {
        return wsJugadoresRawRestApi.deleteById(id);
    }


}
