package es.upsa.dasi.trabajo1.domain.exceptions;

public class JugadorNotFoundAppException extends NotFoundAppException{
    public JugadorNotFoundAppException() {
        super("El jugador no existe");
    }
}
