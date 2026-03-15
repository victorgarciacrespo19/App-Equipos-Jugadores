package es.upsa.dasi.trabajo1.wscontratos.application;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public interface RemoveContratoByIdUseCase
{
    void execute(String id)throws EquiposAppException;
}
