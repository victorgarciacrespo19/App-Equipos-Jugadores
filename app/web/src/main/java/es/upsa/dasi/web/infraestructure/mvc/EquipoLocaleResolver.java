package es.upsa.dasi.web.infraestructure.mvc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.locale.LocaleResolverContext;
import jakarta.ws.rs.core.UriInfo;

import java.util.Locale;
@ApplicationScoped
public class EquipoLocaleResolver implements LocaleResolver
{
    @Override
    public Locale resolveLocale(LocaleResolverContext localeResolverContext)
    {
        UriInfo uriInfo = localeResolverContext.getUriInfo();
        String localeSegment = uriInfo.getPathSegments().getFirst().getPath();
        return Locale.forLanguageTag( localeSegment );
    }
}
