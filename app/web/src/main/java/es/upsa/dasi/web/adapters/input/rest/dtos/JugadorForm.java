package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class JugadorForm {
    @FormParam("nombre")
    private String nombre;

    @FormParam("valorMercado")
    private String valorMercado;

    @FormParam("foto")
    private String foto;

    @FormParam("nacionalidad")
    private String nacionalidad;

    @FormParam("id")
    private String id;

}
