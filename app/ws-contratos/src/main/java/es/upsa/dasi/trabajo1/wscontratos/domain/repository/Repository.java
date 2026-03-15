package es.upsa.dasi.trabajo1.wscontratos.domain.repository;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    Optional<Contrato> findById(String id) throws EquiposAppException;
    List<Contrato> findByIdEquipo(String idEquipo) throws EquiposAppException;
    Contrato save(Contrato credito) throws EquiposAppException;
    void removeById(String id) throws EquiposAppException;
}
