package es.upsa.dasi.web.application.equipos;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;

import java.util.Optional;

public interface UpdateEquipoUsecase
{
    Optional<Equipo> execute(String id, EquipoDto equipoDto);
}
