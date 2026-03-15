package es.upsa.dasi.trabajo1.gateway.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws.WsEquiposRawRestApi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;

@Path("/equipos")
public class EquipoResources
{
    @Inject
    @RestClient
    WsEquiposRawRestApi equiposRawRestApi;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEquipos() throws EquiposAppException
    {
        return equiposRawRestApi.findAll();
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPeliculaById(@PathParam("id") String id) throws EquiposAppException
    {
        return equiposRawRestApi.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EquipoDto equipoDto, @Context UriInfo uriInfo) throws EquiposAppException
    {
        URI baseUri = uriInfo.getBaseUri();
        String scheme = baseUri.getScheme();
        String host = baseUri.getHost();
        String port = String.valueOf( baseUri.getPort() );

        return equiposRawRestApi.create(equipoDto, scheme, host, port);
    }
    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(EquipoDto equipoDto, @PathParam("id") String id) throws EquiposAppException
    {
        return equiposRawRestApi.update(id, equipoDto);
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") String id) throws EquiposAppException
    {
        return equiposRawRestApi.deleteById(id);
    }






}
