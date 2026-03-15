package es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers.WsJugadoresResponseExceptionMapper;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.providers.StringToListParamConverterProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8083")
@RegisterProvider(StringToListParamConverterProvider.class)
@RegisterProvider(WsJugadoresResponseExceptionMapper.class)
@Path("/jugadores")
public interface JugadoresRestClient {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Jugador> findAll(@QueryParam("ids") List<String> ids) throws EquiposAppException;
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Jugador findById(@PathParam("id") String id) throws EquiposAppException;


}
