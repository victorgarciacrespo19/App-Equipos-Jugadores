package es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers.WsContratosResponseExceptionMapper;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers.WsEquiposResponseExceptionMapper;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.providers.StringToListParamConverterProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(baseUri = "http://localhost:8082")
@RegisterProvider(StringToListParamConverterProvider.class)
@RegisterProvider(WsEquiposResponseExceptionMapper.class)
@Path("/equipos")
public interface EquiposRestClient
{
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Equipo findById(@PathParam("id") String id) throws EquiposAppException;
}
