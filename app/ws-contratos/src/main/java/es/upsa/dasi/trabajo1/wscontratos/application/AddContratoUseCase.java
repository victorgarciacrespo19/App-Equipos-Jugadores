package es.upsa.dasi.trabajo1.wscontratos.application;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public interface AddContratoUseCase {
    Contrato execute(Contrato contrato) throws EquiposAppException;

}
