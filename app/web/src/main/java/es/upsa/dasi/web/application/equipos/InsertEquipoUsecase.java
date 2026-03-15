package es.upsa.dasi.web.application.equipos;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;

public interface InsertEquipoUsecase
{
    Equipo execute(EquipoDto equipoDto);
}
