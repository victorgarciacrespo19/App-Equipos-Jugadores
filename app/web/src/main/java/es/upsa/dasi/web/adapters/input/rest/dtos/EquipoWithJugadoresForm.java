package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.ws.rs.FormParam;
import lombok.Data;

import java.util.List;

@Data
public class EquipoWithJugadoresForm {

    @FormParam("equipo")
    private EquipoForm equipoForm;

    @FormParam("jugadores")
    private List<JugadorEquipoForm> jugadores;
}
