package es.upsa.dasi.web.infraestructure.rest.providers;

import es.upsa.dasi.trabajo1.domain.dtos.ErrorDto;
import es.upsa.dasi.web.domain.exceptions.EquipoNotFoundRuntimeException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class EquiposResponseExceptionMapper implements ResponseExceptionMapper<jakarta.ws.rs.WebApplicationException>
{
    @Override
    public jakarta.ws.rs.WebApplicationException toThrowable(Response response) {
        Response.Status status = response.getStatusInfo().toEnum();
        return switch (status)
        {
            case NOT_FOUND -> new EquipoNotFoundRuntimeException();

            default        -> {
                ErrorDto errorDto = response.readEntity(ErrorDto.class);
                yield new InternalServerErrorException( errorDto.getMessage() );
            }
        };
    }
}
