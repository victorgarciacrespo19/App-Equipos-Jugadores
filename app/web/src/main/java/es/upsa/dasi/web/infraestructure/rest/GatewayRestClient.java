package es.upsa.dasi.web.infraestructure.rest;


import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;
import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.dtos.JugadorDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.web.infraestructure.rest.providers.EquiposResponseExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8085")
@RegisterProvider(EquiposResponseExceptionMapper.class)
public interface GatewayRestClient
{
    @GET
    @Path("/equipos")
    @Produces(MediaType.APPLICATION_JSON)
    List<Equipo> findEquipos();

    @GET
    @Path("/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Equipo findEquipoById(@PathParam("id") String id);

    @PUT
    @Path("/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Equipo updateEquipoById(@PathParam("id") String id, EquipoDto equipoDto);

    @DELETE
    @Path("/equipos/{id}")
    public Response deleteEquipoById(@PathParam("id") String id);

    @POST
    @Path("/equipos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Equipo insertEquipo(EquipoDto equipoDto);


    @GET
    @Path("/contratos/equipos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    EquipoWithContratos findEquipoWithContratos(@PathParam("id") String id);



}
