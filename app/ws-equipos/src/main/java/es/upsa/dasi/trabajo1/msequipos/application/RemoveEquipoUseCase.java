package es.upsa.dasi.trabajo1.msequipos.application;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public interface RemoveEquipoUseCase
{
    void execute(String id) throws EquiposAppException;
}
