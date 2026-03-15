package es.upsa.dasi.trabajo1.aggregator.adapters.input.rest;

import es.upsa.dasi.trabajo1.aggregator.application.FindEquipoWithContratosByIdUseCase;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contratos")
public class ContratosResource
{
    @Inject
    FindEquipoWithContratosByIdUseCase findEquipoWithContratosByIdUseCase;

    @Path("/equipos/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEquipoById(@PathParam("id") String id) throws EquiposAppException
    {
        return findEquipoWithContratosByIdUseCase.execute(id)
                .map(equipoWithContratos -> Response.ok()
                                                    .entity(equipoWithContratos)
                                                    .build()
                    )
                .orElseThrow(() -> new EquipoNotFoundAppException());
    }

}
