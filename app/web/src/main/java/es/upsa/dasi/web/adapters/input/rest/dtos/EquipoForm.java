package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class EquipoForm
{
    @NotNull
    @MvcBinding
    @Size(min = 1, max = 100)
    @FormParam("nombre")
    @NotBlank
    private String nombre;

    @NotNull
    @MvcBinding
    @FormParam("estadio")
    @Size(min = 1, max = 100)
    @NotBlank
    private String estadio;

    @NotNull
    @MvcBinding
    @FormParam("ciudad")
    @Size(min = 1, max = 50)
    @NotBlank
    private String ciudad;

    @NotNull
    @MvcBinding
    @FormParam("fundacion")
    @Size(min = 4, max = 4)
    @NotBlank
    private String fundacion;

    @DecimalMin(value = "0", inclusive = true)
    @FormParam("presupuesto")
    @MvcBinding
    @Digits(integer = 10, fraction = 2)
    private double presupuesto;

    @NotNull
    @FormParam("escudo")
    @MvcBinding
    @Size(min = 1, max = 200)
    @NotBlank
    private String escudo;
}
