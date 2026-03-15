package es.upsa.dasi.trabajo1.gateway.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.dtos.ContratoDto;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws.WsAggregatorRawRestApi;
import es.upsa.dasi.trabajo1.gateway.infraestructure.rest.restapis.raws.WsContratosRawRestApi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;

@Path("/contratos")
public class ContratosResource
{
    @Inject
    @RestClient
    WsAggregatorRawRestApi wsAggregatorRawRestApi;
    @Inject
    @RestClient
    WsContratosRawRestApi wsContratosRawRestApi;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findContratoById(@PathParam("id") String id) throws EquiposAppException
    {
        return wsContratosRawRestApi.findById(id);
    }

    @Path("/equipos/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPeliculaWithCreditosById(@PathParam("id") String id) throws EquiposAppException
    {
        return wsAggregatorRawRestApi.findEquipoWithContratosById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ContratoDto contratoDto, @Context UriInfo uriInfo) throws EquiposAppException
    {
        URI baseUri = uriInfo.getBaseUri();
        String scheme = baseUri.getScheme();
        String host = baseUri.getHost();
        String port = String.valueOf(baseUri.getPort());
        return wsContratosRawRestApi.create(contratoDto, scheme, host, port);
    }

    @DELETE
    @Path("/{id}")
    public Response removeContratosById(@PathParam("id") String id) throws EquiposAppException
    {
        return wsContratosRawRestApi.deleteById(id);
    }

}
