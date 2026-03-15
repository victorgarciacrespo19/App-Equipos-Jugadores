package es.upsa.dasi.trabajo1.aggregator.application.impl;

import es.upsa.dasi.trabajo1.aggregator.application.FindEquipoWithContratosByIdUseCase;
import es.upsa.dasi.trabajo1.aggregator.domain.repository.Repository;
import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;
import es.upsa.dasi.trabajo1.domain.aggregators.JugadorEquipo;
import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class FindEquipoWithContratosByIdUseCaseImpl implements FindEquipoWithContratosByIdUseCase
{
    @Inject
    Repository repository;
    @Override
    public Optional<EquipoWithContratos> execute(String id) throws EquiposAppException {

        Optional<Equipo> optionalEquipo = repository.findEquipoById(id);
        if (optionalEquipo.isPresent())
        {
          Equipo equipo = optionalEquipo.get();
            List<Contrato> contratosEquipo = repository.findContratosByEquipoId(equipo.getId());
            List<String> jugadoresIds = contratosEquipo.stream()
                                                       .map(contrato -> contrato.getJugador())
                                                       .distinct()
                                                       .toList();

            Map<String, Jugador> jugadores = repository.findJugadorByIds(jugadoresIds)
                                                       .stream()
                                                       .collect(Collectors.toMap(jugador -> jugador.getIdJugador(), jugador -> jugador));

            EquipoWithContratos.EquipoWithContratosBuilder builder = EquipoWithContratos.builder()
                                                                                        .withEquipo(equipo);
            contratosEquipo.forEach(contrato -> {
                                                    Jugador jugador = jugadores.get(contrato.getJugador());
                                                    builder.withJugador(JugadorEquipo.builder()
                                                                                     .withFin_contrato(contrato.getFinContrato())
                                                                                     .withJugador(Jugador.builder()
                                                                                                         .withNombre(jugador.getNombre())
                                                                                                         .withValorMercado(jugador.getValorMercado())
                                                                                                         .withFoto(jugador.getFoto())
                                                                                                         .build())

                                                            .build());

                                                });

            return Optional.of(builder.build());
        }
        return Optional.empty();
    }
}
