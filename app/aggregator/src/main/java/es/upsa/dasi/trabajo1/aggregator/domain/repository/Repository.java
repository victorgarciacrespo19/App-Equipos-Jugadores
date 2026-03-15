package es.upsa.dasi.trabajo1.aggregator.domain.repository;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    Optional<Equipo> findEquipoById(String id) throws EquiposAppException;

    List<Jugador> findJugadorByIds(List<String> ids) throws EquiposAppException;
    Optional<Jugador> findJugadorById(String id) throws EquiposAppException;

    List<Contrato> findContratosByEquipoId(String id) throws EquiposAppException;


}
