package es.upsa.dasi.trabajo1.msequipos.application;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.Optional;

public interface GetEquipoByIdUseCase {
    public Optional<Equipo> execute(String id) throws EquiposAppException;
}
