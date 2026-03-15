package es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi;


import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers.WsContratosResponseExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8084")
@RegisterProvider(WsContratosResponseExceptionMapper.class)
@Path("/contratos")
public interface ContratosRestClient
{
    @GET
    @Path("/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Contrato> findContratoByEquipoId(@PathParam("id") String id) throws EquiposAppException;

}
