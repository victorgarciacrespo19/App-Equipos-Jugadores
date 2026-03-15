package es.upsa.dasi.trabajo1.msequipos.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.mappers.Mappers;
import es.upsa.dasi.trabajo1.msequipos.application.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/equipos")
public class EquiposResource {

    @Inject
    GetEquipoUseCase getEquipoUseCase;
    @Inject
    GetEquipoByIdUseCase getEquipoByIdUseCase;
    @Inject
    AddEquipoUseCase addEquipoUseCase;
    @Inject
    UpdateEquipoUseCase updateEquipoUseCase;
    @Inject
    RemoveEquipoUseCase removeEquipoUseCase;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEquipos() throws EquiposAppException
    {
        List<Equipo> equipos = getEquipoUseCase.execute();
        return Response.ok()
                       .entity(new GenericEntity<List<Equipo>>(equipos){})
                       .build();
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEquiposById(@PathParam("id") String id) throws EquiposAppException
    {
        Optional<Equipo> optEquipo = getEquipoByIdUseCase.execute(id);

        return optEquipo.map(equipo -> Response.ok()
                                               .entity(equipo)
                                               .build()
                )
                .orElseThrow(() -> new EquipoNotFoundAppException());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEquipo(EquipoDto equipoDto, @Context UriInfo uriInfo) throws EquiposAppException
    {
        Equipo equipo = Mappers.toEquipo(equipoDto);
        Equipo insertedEquipo = addEquipoUseCase.execute(equipo);
        return Response.created(createEquipoURI(uriInfo, insertedEquipo))
                       .entity(insertedEquipo)
                       .build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEquipo(@PathParam("id") String id, EquipoDto equipoDto) throws EquiposAppException
    {
        Equipo equipo = Mappers.toEquipo(equipoDto).withId(id);
        Equipo updatedEquipo = updateEquipoUseCase.execute(equipo);
        return Response.ok()
                .entity( updatedEquipo )
                .build();
    }
    @Path("/{id}")
    @DELETE
    public Response deleteEquipoById(@PathParam("id") String id) throws EquiposAppException
    {
        removeEquipoUseCase.execute(id);
        return Response.noContent()
                       .build();
    }


    private URI createEquipoURI(UriInfo uriInfo, Equipo equipo)
    {
        return uriInfo.getBaseUriBuilder()
                      .path("/equipo/{id}")
                      .resolveTemplate("id", equipo.getId())
                      .build();

    }

}
