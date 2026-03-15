package es.upsa.dasi.web.adapters.input.rest.mappers;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.dtos.JugadorDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.web.adapters.input.rest.dtos.EquipoForm;
import es.upsa.dasi.web.adapters.input.rest.dtos.JugadorForm;

public class Mapper
{
    public static EquipoDto toEquipoDto(EquipoForm equipoForm)
    {
        return EquipoDto.builder()
                .withNombre(equipoForm.getNombre())
                .withCiudad(equipoForm.getCiudad())
                .withPresupuesto(equipoForm.getPresupuesto())
                .withFundacion(equipoForm.getFundacion())
                .withEstadio(equipoForm.getEstadio())
                .withEscudo(equipoForm.getEscudo())
                .build();
    }
}
