package es.upsa.dasi.trabajo1.msequipos.domain.exceptions;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public class UniqueNameSQLException extends EquiposAppException {
    public UniqueNameSQLException() {
        super("Nombre de Equipo ya existente");
    }
}
