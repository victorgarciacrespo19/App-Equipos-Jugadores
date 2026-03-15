package es.upsa.dasi.trabajo1.wscontratos.application;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;

public interface GetContratosEquipoUseCase
{
    List<Contrato> execute(String idEquipo) throws EquiposAppException;
}
