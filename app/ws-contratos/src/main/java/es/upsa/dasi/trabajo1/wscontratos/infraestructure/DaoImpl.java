package es.upsa.dasi.trabajo1.wscontratos.infraestructure;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.*;
import es.upsa.dasi.trabajo1.wscontratos.adapters.output.persistance.Dao;
import es.upsa.dasi.trabajo1.wscontratos.domain.exceptions.FieldRequiredSQLException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {
    @Inject
    DataSource dataSource;

    @Override
    public Optional<Contrato> selectById(String id) throws EquiposAppException
    {
        final String SQL = """
                           SELECT c.id, c.id_jugador, c.id_equipo, c.fin_contrato
                             FROM contratos c
                            WHERE c.id = ?
                           """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        )
        {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                return resultSet.next()? Optional.of( toContrato(resultSet) ) : Optional.empty();
            }
        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public List<Contrato> selectByIdEquipo(String idEquipo) throws EquiposAppException
    {
        final String SQL = """
                           SELECT c.id, c.id_jugador, c.id_equipo, c.fin_contrato
                             FROM contratos c
                            WHERE c.id_equipo = ?
                           """;
        List<Contrato> contratos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        )
        {
            preparedStatement.setString(1, idEquipo);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next()) contratos.add( toContrato(resultSet) );
            }
            return contratos;

        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }

    }


    @Override
    public Contrato insert(Contrato contrato) throws EquiposAppException {
        final String SQL = """
                           INSERT INTO contratos(id,                      id_jugador, id_equipo, fin_contrato)  
                                         VALUES(nextval('seq_creditos'), ?    ,          ?   ,           ?      )   
                           """;
        final String[] fields = {"id"};
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields)
        )
        {
            preparedStatement.setString(1, contrato.getJugador());
            preparedStatement.setString(2, contrato.getEquipo());
            preparedStatement.setString(3, contrato.getFinContrato());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                String id = resultSet.getString(1);
                return contrato.withId(id);
            }
        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public void deleteById(String id) throws EquiposAppException
    {
        final String SQL = """
                           DELETE 
                             FROM contratos
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new ContratoNotFoundAppException();
        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    private Contrato toContrato(ResultSet resultSet) throws SQLException
    {
        return Contrato.builder()
                .withId(resultSet.getString("id"))
                .withJugador(resultSet.getString("id_jugador"))
                .withEquipo(resultSet.getString("id_equipo"))
                .withFinContrato(resultSet.getString("fin_contrato"))
                .build();
    }
    private EquiposAppException toEquiposAppException(SQLException sqlException)
    {
        String message = sqlException.getMessage();
        if      ( message.contains("NN_FIN_CONTRATO")  ) return new FieldRequiredSQLException("fin contrato");
        else if ( message.contains("FK_CONTRATOS_EQUIPOS")   ) return new EquipoNotFoundAppException();
        else if ( message.contains("FK_CONTRATOS_JUGADORES")    ) return new JugadorNotFoundAppException();

        return new SQLEquiposAppException(sqlException);
    }
}
