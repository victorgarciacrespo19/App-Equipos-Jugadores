package es.upsa.dasi.trabajo1.shared.quarkus.adapters.rest.providers;

import es.upsa.dasi.trabajo1.domain.dtos.ErrorDto;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.NotFoundAppException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EquiposApplicationExceptionMapper implements ExceptionMapper<EquiposAppException>
{

    @Override
    public Response toResponse(EquiposAppException exception) {
        String message = exception.getMessage();
        Response.Status status = (exception instanceof NotFoundAppException) ? Response.Status.NOT_FOUND : Response.Status.INTERNAL_SERVER_ERROR;
        return Response.status(status)
                .entity(ErrorDto.builder()
                                .withMessage(message)
                                .build()
                       )
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
