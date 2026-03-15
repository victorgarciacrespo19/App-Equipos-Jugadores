package es.upsa.dasi.trabajo1.wscontratos.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.dtos.ContratoDto;
import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.ContratoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.mappers.Mappers;
import es.upsa.dasi.trabajo1.wscontratos.application.AddContratoUseCase;
import es.upsa.dasi.trabajo1.wscontratos.application.GetContratoByIdUseCase;
import es.upsa.dasi.trabajo1.wscontratos.application.GetContratosEquipoUseCase;
import es.upsa.dasi.trabajo1.wscontratos.application.RemoveContratoByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@Path("/contratos")
public class ContratosResource {
    @Inject
    Logger log;
    @Inject
    GetContratoByIdUseCase getContratoByIdUseCase;
    @Inject
    GetContratosEquipoUseCase getContratosEquipoUsecase;

    @Inject
    AddContratoUseCase addContratoUsecase;

    @Inject
    RemoveContratoByIdUseCase removeContratoByIdUseCase;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoById(@PathParam("id") String id) throws EquiposAppException
    {
        return getContratoByIdUseCase.execute(id).map(contrato -> Response.ok()
                                                                          .entity(contrato)
                                                                          .build()
                                                     )
                                                .orElseThrow(()-> new ContratoNotFoundAppException());
    }
    @GET
    @Path("/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratosEquipo(@PathParam("id")String idEquipo) throws EquiposAppException
    {
        List<Contrato> contratos = getContratosEquipoUsecase.execute(idEquipo);
        return Response.ok()
                       .entity(new GenericEntity<List<Contrato>>(contratos){})
                       .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addContrato(ContratoDto contratoDto, @Context UriInfo uriInfo,  @DefaultValue("") @HeaderParam("X-Forwarded-Prefix") String prefix) throws EquiposAppException
    {
        Contrato contrato = addContratoUsecase.execute(Mappers.toContrato(contratoDto));
        return Response.created(createContratoURI(uriInfo,contrato, prefix))
                        .entity(contrato)
                        .build();
    }

    @Path("/{id}")
    @DELETE
    public Response removeCreditoById(@PathParam("id") String id) throws EquiposAppException
    {
        removeContratoByIdUseCase.execute(id);
        return Response.noContent()
                       .build();
    }

    private URI createContratoURI(UriInfo uriInfo, Contrato contrato, String prefix)
    {
        return uriInfo.getBaseUriBuilder()
                .path(prefix)
                .path("/contratos/{id}")
                .resolveTemplate("id", contrato.getId())
                .build();
    }

}
