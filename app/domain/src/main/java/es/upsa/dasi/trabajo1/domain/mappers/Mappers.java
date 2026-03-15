package es.upsa.dasi.trabajo1.domain.mappers;

import es.upsa.dasi.trabajo1.domain.dtos.ContratoDto;
import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.dtos.JugadorDto;
import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;

public class Mappers
{
    public static Equipo toEquipo(EquipoDto equipoDto)
    {
        return Equipo.builder()
                     .withId(null)
                     .withNombre(equipoDto.getNombre())
                     .withCiudad(equipoDto.getCiudad())
                     .withEstadio(equipoDto.getEstadio())
                     .withPresupuesto(equipoDto.getPresupuesto())
                     .withFundacion(equipoDto.getFundacion())
                     .withEscudo(equipoDto.getEscudo())
                     .build();
    }

    public static EquipoDto toEquipoDto(Equipo equipo)
    {
        return EquipoDto.builder()
                        .withNombre(equipo.getNombre())
                        .withCiudad(equipo.getCiudad())
                        .withPresupuesto(equipo.getPresupuesto())
                        .withFundacion(equipo.getFundacion())
                        .withEstadio(equipo.getEstadio())
                        .withEscudo(equipo.getEscudo())
                        .build();
    }
    public static Jugador toJugador(JugadorDto jugadorDto)
    {
        return Jugador.builder()
                .withIdJugador(null)
                .withNombre(jugadorDto.getNombre())
                .withNacionalidad(jugadorDto.getNacionalidad())
                .withFoto(jugadorDto.getFoto())
                .withValorMercado(jugadorDto.getValorMercado())
                .build();
    }

    public static JugadorDto toJugadorDto(Jugador jugador)
    {
        return JugadorDto.builder()
                         .withNombre(jugador.getNombre())
                         .withNacionalidad(jugador.getNacionalidad())
                         .withFoto(jugador.getFoto())
                         .withValorMercado(jugador.getValorMercado())
                         .build();
    }

    public static Contrato toContrato(ContratoDto contratoDto)
    {
        return Contrato.builder()
                .withId(null)
                .withJugador(contratoDto.getJugador())
                .withEquipo(contratoDto.getEquipo())
                .withFinContrato(contratoDto.getFinContrato())
                .build();
    }
    public static ContratoDto contratoDto (Contrato contrato)
    {
        return ContratoDto.builder()
                .withJugador(contrato.getJugador())
                .withEquipo(contrato.getEquipo())
                .withFinContrato(contrato.getFinContrato())
                .build();
    }

}
