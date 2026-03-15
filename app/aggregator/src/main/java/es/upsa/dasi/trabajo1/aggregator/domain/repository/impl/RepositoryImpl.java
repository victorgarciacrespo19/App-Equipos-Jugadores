package es.upsa.dasi.trabajo1.aggregator.domain.repository.impl;

import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.ContratosDao;
import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.EquiposDao;
import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.JugadoresDao;
import es.upsa.dasi.trabajo1.aggregator.domain.repository.Repository;
import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    @Inject
    ContratosDao contratosDao;
    @Inject
    JugadoresDao jugadoresDao;
    @Inject
    EquiposDao equiposDao;


    @Override
    public Optional<Equipo> findEquipoById(String id) throws EquiposAppException {
        return equiposDao.findById(id);
    }

    @Override
    public List<Jugador> findJugadorByIds(List<String> ids) throws EquiposAppException {
        return jugadoresDao.findAll(ids);
    }

    @Override
    public Optional<Jugador> findJugadorById(String id) throws EquiposAppException {
        return jugadoresDao.findById(id);
    }

    @Override
    public List<Contrato> findContratosByEquipoId(String id) throws EquiposAppException {
        return contratosDao.findByEquipoId(id);
    }
}
