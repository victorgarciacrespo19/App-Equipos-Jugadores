package es.upsa.dasi.trabajo1.msequipos.application;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;

public interface GetEquipoUseCase {
    List<Equipo> execute() throws EquiposAppException;
}
