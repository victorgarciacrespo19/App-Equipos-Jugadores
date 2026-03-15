package es.upsa.dasi.trabajo1.wsjugadores.domain.exceptions;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public class FieldRequiredSQLException extends EquiposAppException
{
    public FieldRequiredSQLException(String fieldName) {
        super("El campo " + fieldName  + " es obligatorio");
    }
}
