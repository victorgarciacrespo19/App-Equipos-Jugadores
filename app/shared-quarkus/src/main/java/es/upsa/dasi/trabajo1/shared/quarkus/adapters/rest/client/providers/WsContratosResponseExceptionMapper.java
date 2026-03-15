package es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo1.domain.exceptions.ContratoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.RestClientAppException;

import java.util.function.Function;
import java.util.function.Supplier;

public class WsContratosResponseExceptionMapper extends WsResponseExceptionMapper<EquiposAppException>{
    public WsContratosResponseExceptionMapper() {
        super((message)-> new RestClientAppException(message), () -> new ContratoNotFoundAppException());
    }
}
