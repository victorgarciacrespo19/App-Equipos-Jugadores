package es.upsa.dasi.trabajo1.aggregator.adapters.output.rest;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;

public interface ContratosDao {
    List<Contrato> findByEquipoId(String id) throws EquiposAppException;
}
