package es.upsa.dasi.trabajo1.domain.exceptions;

public class EquipoNotFoundAppException extends NotFoundAppException {
    public EquipoNotFoundAppException() {
        super("El equipo no existe");
    }
}
