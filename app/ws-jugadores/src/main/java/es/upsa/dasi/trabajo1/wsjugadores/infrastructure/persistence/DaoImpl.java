package es.upsa.dasi.trabajo1.wsjugadores.infrastructure.persistence;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.JugadorNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.SQLEquiposAppException;
import es.upsa.dasi.trabajo1.wsjugadores.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo1.wsjugadores.domain.exceptions.FieldRequiredSQLException;
import es.upsa.dasi.trabajo1.wsjugadores.domain.exceptions.JugadorHasContractSQLException;
import es.upsa.dasi.trabajo1.wsjugadores.domain.exceptions.UniqueNameSQLException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{

    @Inject
    DataSource dataSource;
    @Override
    public List<Jugador> findJugadores() throws EquiposAppException {
        final String SQL = """
                           SELECT j.id, j.nombre, j.nacionalidad, j.foto, j.valor_mercado
                           FROM jugadores j
                           """;
        List<Jugador> jugadores = new ArrayList<>();
        try (Connection connection= dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)
            )
        {
            while (resultSet.next()) {
                Jugador jugador = toJugador(resultSet);
                jugadores.add(jugador);
            }
            return jugadores;

        }catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Optional<Jugador> findJugadorById(String id) throws EquiposAppException
    {
        final String SQL = """
                           SELECT j.id, j.nombre, j.nacionalidad, j.foto, j.valor_mercado
                           FROM jugadores j 
                           WHERE j.id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)
            )
        {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            return ( !resultSet.next() )? Optional.empty() : Optional.of( toJugador(resultSet) );

        }catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Jugador insertJugador(Jugador jugador) throws EquiposAppException
    {
        final String SQL = """
                           INSERT INTO jugadores(id                      , nombre, nacionalidad, foto, valor_mercado)
                                         VALUES (nextval('seq_jugadores'),    ?  ,    ?        ,   ? ,      ?       )
                           """;
        final String[] fields = {"id"};

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields);
            )
        {
            preparedStatement.setString(1, jugador.getNombre());
            preparedStatement.setString(2, jugador.getNacionalidad());
            preparedStatement.setString(3, jugador.getFoto());
            preparedStatement.setString(4, jugador.getValorMercado());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                String id = resultSet.getString( 1) ;
                return jugador.withIdJugador(id);
            }
        }catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Optional<Jugador> updateJugador(Jugador jugador) throws EquiposAppException {
        final String SQL = """
                           UPDATE jugadores 
                           SET nombre = ?,nacionalidad = ?,  foto = ?, valor_mercado = ?
                           WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
            )
        {
            preparedStatement.setString(1, jugador.getNombre());
            preparedStatement.setString(2, jugador.getNacionalidad());
            preparedStatement.setString(3, jugador.getFoto());
            preparedStatement.setString(4, jugador.getValorMercado());
            preparedStatement.setString(5, jugador.getIdJugador());
            int count = preparedStatement.executeUpdate();
            return (count == 0)? Optional.empty() : Optional.of(jugador);

        }catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public void deleteJugadorById(String id) throws EquiposAppException
    {
        final String SQL = """
                           DELETE 
                             FROM jugadores
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
            )
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new JugadorNotFoundAppException();
        }catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    private Jugador toJugador(ResultSet resultSet) throws SQLException
    {
        return Jugador.builder()
                .withIdJugador(resultSet.getString(1) )
                .withNombre(resultSet.getString(2))
                .withNacionalidad(resultSet.getString(3))
                .withFoto(resultSet.getString(4))
                .withValorMercado(resultSet.getString(5))
                .build();
    }
    private EquiposAppException toEquiposAppException(SQLException sqlException)
    {
        String message = sqlException.getMessage();
        if      ( message.contains("NN_JUGADORES_NOMBRE")   ) return new FieldRequiredSQLException("nombre");
        else if (message.contains("UQ_JUGADORES_NOMBRE")) return new UniqueNameSQLException();
        else if ( message.contains("NN_JUGADORES_NACIONALIDAD")  ) return new FieldRequiredSQLException("nacionalidad");
        else if ( message.contains("NN_JUGADORES_VALOR_MERCADO") ) return new FieldRequiredSQLException("foto");
        else if (message.contains("FK_CONTRATOS_JUGADORES") ) return new JugadorHasContractSQLException();

        return new SQLEquiposAppException(sqlException);
    }
}
