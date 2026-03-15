package es.upsa.dasi.trabajo1.aggregator.implementarion.rest;

import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.ContratosDao;
import es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi.ContratosRestClient;
import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ContratosDaoImpl implements ContratosDao
{
    @Inject
    @RestClient
    ContratosRestClient restClient;
    @Override
    public List<Contrato> findByEquipoId(String id) throws EquiposAppException {
        return restClient.findContratoByEquipoId(id);
    }
}
