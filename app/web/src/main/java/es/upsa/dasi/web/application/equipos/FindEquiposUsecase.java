package es.upsa.dasi.web.application.equipos;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;

public interface FindEquiposUsecase
{
    List<Equipo> execute() throws EquiposAppException;
}
