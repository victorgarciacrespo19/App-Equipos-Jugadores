package es.upsa.dasi.trabajo1.msequipos.domain.exceptions;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public class ClubHasContractSQLException extends EquiposAppException {
    public ClubHasContractSQLException() {
        super("El club tiene contratos con jugadores en vigor");
    }
}
