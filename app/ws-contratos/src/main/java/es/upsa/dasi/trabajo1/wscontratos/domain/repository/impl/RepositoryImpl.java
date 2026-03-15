package es.upsa.dasi.trabajo1.wscontratos.domain.repository.impl;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wscontratos.adapters.output.persistance.Dao;
import es.upsa.dasi.trabajo1.wscontratos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    Dao dao;

    @Override
    public Optional<Contrato> findById(String id) throws EquiposAppException {
        return dao.selectById(id);
    }

    @Override
    public List<Contrato> findByIdEquipo(String idEquipo) throws EquiposAppException {
        return dao.selectByIdEquipo(idEquipo);
    }

    @Override
    public Contrato save(Contrato credito) throws EquiposAppException {
        return dao.insert(credito);
    }

    @Override
    public void removeById(String id) throws EquiposAppException {
        dao.deleteById(id);
    }



}
