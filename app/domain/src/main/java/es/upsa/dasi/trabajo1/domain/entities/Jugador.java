package es.upsa.dasi.trabajo1.domain.entities;

import lombok.*;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@With
public class Jugador {
    private String idJugador;
    private String nombre;
    private String nacionalidad;
    private String foto;
    private String valorMercado;
}
