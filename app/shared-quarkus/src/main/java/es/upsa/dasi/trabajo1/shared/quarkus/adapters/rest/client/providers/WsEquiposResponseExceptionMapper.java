package es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.RestClientAppException;

import java.util.function.Function;
import java.util.function.Supplier;

public class WsEquiposResponseExceptionMapper extends WsResponseExceptionMapper<EquiposAppException>{
    public WsEquiposResponseExceptionMapper() {
        super((message)-> new RestClientAppException(message), () -> new EquipoNotFoundAppException());
    }
}
