package es.upsa.dasi.web.domain.exceptions;

import es.upsa.dasi.trabajo1.domain.exceptions.NotFoundAppException;
import jakarta.ws.rs.NotFoundException;

public class EquipoNotFoundRuntimeException extends NotFoundException {
    public EquipoNotFoundRuntimeException() {
        super("Equipo no encontrado");
    }
}
