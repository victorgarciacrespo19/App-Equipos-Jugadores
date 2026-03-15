package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.application.equipos.FindEquipoByIdUsecase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/{locale}/forms")
@ApplicationScoped
public class FormsEquipoResource
{
    @Inject
    FindEquipoByIdUsecase findEquipoByIdUsecase;

    @Inject
    Models models;

    @GET
    @Path("/update/equipo/{id}")
    @Controller
    @UriRef("formUpdateEquipoById")
    public Response formUpdateEquipoById(@PathParam("id") String id)
    {
        Optional<Equipo> optionalEquipo = findEquipoByIdUsecase.execute(id);
        if (optionalEquipo.isEmpty()) return Response.ok("/jsps/equipoNotFound.jsp").build();

        models.put("action", Action.UPDATE);
        models.put("equipo", optionalEquipo.get());
        return Response.ok("/jsps/equipo.jsp").build();
    }


    @GET
    @Path("/delete/equipo/{id}")
    @Controller
    @UriRef("formDeleteEquipoById")
    public Response formDeleteEquipoById(@PathParam("id") String id)
    {
        Optional<Equipo> optionalEquipo = findEquipoByIdUsecase.execute(id);
        if (optionalEquipo.isEmpty()) return Response.ok("/jsps/equipoNotFound.jsp").build();

        models.put("action", Action.DELETE);
        models.put("equipo", optionalEquipo.get());
        return Response.ok("/jsps/equipo.jsp").build();
    }

    @GET
    @Path("/insert/equipo")
    @Controller
    @UriRef("formInsertEquipo")
    public Response formInsertEquipo()
    {
        models.put("action", Action.INSERT);
        return Response.ok("/jsps/equipo.jsp").build();
    }

}
