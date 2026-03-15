package es.upsa.dasi.trabajo1.msequipos.domain.repository.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo1.msequipos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class RepositoryImpl implements Repository {
    @Inject
    Dao dao;
    @Override
    public List<Equipo> getEquipos() throws EquiposAppException {
        List<Equipo> equipos = dao.findEquipos();
        return equipos;
    }

    @Override
    public Optional<Equipo> getEquiposById(String id) throws EquiposAppException {
        return dao.findEquipoById(id);
    }

    @Override
    public Equipo saveEquipo(Equipo equipo) throws EquiposAppException {
        return (equipo.getId() == null)? dao.insertEquipo(equipo): dao.updateEquipo(equipo)
                                                                      .orElseThrow(() -> new EquipoNotFoundAppException());
    }

    @Override
    public void removeEquipoById(String id) throws EquiposAppException {
        dao.deleteEquipoById(id);
    }
}
