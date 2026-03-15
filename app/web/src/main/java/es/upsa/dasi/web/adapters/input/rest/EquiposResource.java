package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;
import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.adapters.input.rest.dtos.EquipoForm;
import es.upsa.dasi.web.adapters.input.rest.mappers.Mapper;
import es.upsa.dasi.web.application.equipos.*;
import es.upsa.dasi.web.domain.exceptions.EquipoNotFoundRuntimeException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/{locale}/equipos")
public class EquiposResource
{
    @Inject
    FindEquiposUsecase findEquiposUsecase;

    @Inject
    FindEquipoWithContratosByIdUsecase findEquipoByIdUsecase;
    @Inject
    UpdateEquipoUsecase updateEquipoUsecase;
    @Inject
    DeleteEquipoById deleteEquipoById;
    @Inject
    InsertEquipoUsecase insertEquipoUsecase;

    @Inject
    MvcContext mvc;

    @Inject
    Models models;

    @Inject
    BindingResult bindingResult;
    @GET
    @Controller
    @UriRef("findEquipos")
    @View("/jsps/equipos.jsp")
    public void findAll() throws EquiposAppException
    {
        List<Equipo> equipos = findEquiposUsecase.execute();
        models.put("equipos", equipos);
    }


    @GET
    @Path("/{id}")
    @Controller
    @UriRef("findEquipoById")
    public Response findEquipoById(@PathParam("id") String id)
    {
        Optional<EquipoWithContratos> optionalEquipoWithContratos = findEquipoByIdUsecase.execute(id);
        if (optionalEquipoWithContratos.isEmpty()) return Response.ok("/jsps/equipoNotFound.jsp").build();

        EquipoWithContratos equipoWithContratos = optionalEquipoWithContratos.get();
        models.put("action", Action.VIEW);
        models.put("equipo", equipoWithContratos.getEquipo());
        models.put("jugadores", equipoWithContratos.getJugadores());

        return Response.ok("/jsps/equipo.jsp").build();

    }

    @PUT
    @Path("/{id}")
    @UriRef("updateEquipoById")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateEquipoById(@PathParam("id") String id,@Valid @BeanParam EquipoForm equipoForm)
    {
        EquipoDto equipoDto = Mapper.toEquipoDto(equipoForm);
        try
        {
            if ( bindingResult.isFailed() )
            {
                Map<String, List<String>> errores = new HashMap<>();

                Set<ParamError> allErrors = bindingResult.getAllErrors();
                for (ParamError paramError : allErrors)
                {
                    List<String> messages = errores.get(paramError.getParamName());
                    if (messages == null) messages = new ArrayList<>();
                    messages.add(paramError.getMessage());

                    errores.put(paramError.getParamName(), messages);
                }


                Map<String, List<String>> errs = bindingResult.getAllErrors()
                        .stream()
                        .collect(Collectors.groupingBy(ParamError::getParamName,
                                        Collectors.mapping(ParamError::getMessage, Collectors.toList())
                                )
                        );


                Equipo equipo = es.upsa.dasi.trabajo1.domain.mappers.Mappers.toEquipo(equipoDto).withId(id);
                models.put("action", Action.UPDATE);
                models.put("equipo", equipo);
                models.put("errores", errs);
                return Response.ok("/jsps/equipo.jsp").build();

            }


            Optional<Equipo> optionalEquipo = updateEquipoUsecase.execute(id, equipoDto);

            if (optionalEquipo.isEmpty()) {
                return Response.ok("/jsps/equipoNotFound.jsp").build();
            }

            return Response.seeOther(mvc.uri("findEquipos", Map.of("locale", mvc.getLocale()))).build();

        } catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/error.jsp").build();
        }
    }

    @POST
    @UriRef("insertEquipo")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertEquipo(@Valid @BeanParam EquipoForm equipoForm)
    {
        EquipoDto equipoDto = Mapper.toEquipoDto(equipoForm);
        try
        {
            if ( bindingResult.isFailed() )
            {
                Map<String, List<String>> errores = new HashMap<>();

                Set<ParamError> allErrors = bindingResult.getAllErrors();
                for (ParamError paramError : allErrors)
                {
                    List<String> messages = errores.get(paramError.getParamName());
                    if (messages == null) messages = new ArrayList<>();
                    messages.add(paramError.getMessage());

                    errores.put(paramError.getParamName(), messages);
                }


                Map<String, List<String>> errs = bindingResult.getAllErrors()
                        .stream()
                        .collect(Collectors.groupingBy(ParamError::getParamName,
                                        Collectors.mapping(ParamError::getMessage, Collectors.toList())
                                )
                        );


                Equipo equipo = es.upsa.dasi.trabajo1.domain.mappers.Mappers.toEquipo(equipoDto);
                models.put("action", Action.INSERT);
                models.put("equipo", equipo);
                models.put("errores", errs);
                return Response.ok("/jsps/equipo.jsp").build();

            }

            Equipo equipo = insertEquipoUsecase.execute(equipoDto);
            return Response.seeOther(mvc.uri("findEquipos", Map.of("locale", mvc.getLocale()))).build();

        } catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/error.jsp").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UriRef("deleteEquipoById")
    @Controller
    public Response deleteEquipoById(@PathParam("id") String id)
    {
        try
        {
            deleteEquipoById.execute(id);
            return Response.seeOther(mvc.uri("findEquipos", Map.of("locale", mvc.getLocale()))).build();

        } catch (EquipoNotFoundRuntimeException exception)
        {
            return Response.ok("/jsps/equiposNotFound.jsp").build();

        }
        catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/error.jsp").build();
        }



    }








}
