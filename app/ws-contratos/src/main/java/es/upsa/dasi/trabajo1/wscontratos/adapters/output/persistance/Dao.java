package es.upsa.dasi.trabajo1.wscontratos.adapters.output.persistance;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    Optional<Contrato> selectById(String id) throws EquiposAppException;
    List<Contrato> selectByIdEquipo(String idEquipo) throws EquiposAppException;
    Contrato insert(Contrato contrato) throws EquiposAppException;
    void deleteById(String id) throws EquiposAppException;
}
