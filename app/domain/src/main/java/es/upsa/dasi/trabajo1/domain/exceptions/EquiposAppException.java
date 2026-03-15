package es.upsa.dasi.trabajo1.domain.exceptions;

public class EquiposAppException extends Exception{
    public EquiposAppException() {
    }

    public EquiposAppException(String message) {
        super(message);
    }

    public EquiposAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquiposAppException(Throwable cause) {
        super(cause);
    }

    public EquiposAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
