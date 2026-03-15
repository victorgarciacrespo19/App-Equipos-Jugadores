package es.upsa.dasi.web.application.equipos;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;

import java.util.Optional;

public interface FindEquipoByIdUsecase
{
    Optional<Equipo> execute(String id);
}
