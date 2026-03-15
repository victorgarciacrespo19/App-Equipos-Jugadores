package es.upsa.dasi.trabajo1.msequipos.application;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public interface UpdateEquipoUseCase
{
    public Equipo execute(Equipo equipo) throws EquiposAppException;
}
