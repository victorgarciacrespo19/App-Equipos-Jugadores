package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class JugadorEquipoForm {
    @FormParam("fin_contrato")
    private String fin_contrato;
    @FormParam("jugador")
    private JugadorForm jugadorForm;
}
