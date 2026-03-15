package es.upsa.dasi.trabajo1.wsjugadores.domain.exceptions;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public class UniqueNameSQLException extends EquiposAppException {
    public UniqueNameSQLException() {
        super("El jugador ya esta añadido");
    }
}
