package es.upsa.dasi.trabajo1.domain.exceptions;

public class ContratoNotFoundAppException extends NotFoundAppException
{
    public ContratoNotFoundAppException() {
        super("El contrato no existe");
    }
}
