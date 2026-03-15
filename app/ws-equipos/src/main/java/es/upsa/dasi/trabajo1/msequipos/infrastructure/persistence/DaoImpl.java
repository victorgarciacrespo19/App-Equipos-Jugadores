package es.upsa.dasi.trabajo1.msequipos.infrastructure.persistence;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.SQLEquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo1.msequipos.domain.exceptions.ClubHasContractSQLException;
import es.upsa.dasi.trabajo1.msequipos.domain.exceptions.FieldRequiredSQLException;
import es.upsa.dasi.trabajo1.msequipos.domain.exceptions.UniqueNameSQLException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {
    @Inject
    DataSource dataSource;

    @Override
    public List<Equipo> findEquipos() throws EquiposAppException {

        final String SQL ="""
                          SELECT e.id, e.nombre, e.estadio, e.ciudad, e.fundacion, e.presupuesto, e.escudo
                          FROM equipos e
                          """;

        List<Equipo> equipos = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        )
        {
            while (resultSet.next())
            {
                Equipo equipo = toEquipo(resultSet);
                equipos.add(equipo);
            }
            return equipos;

        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Optional<Equipo> findEquipoById(String id) throws EquiposAppException {

        final String SQL ="""
                          SELECT e.id, e.nombre, e.estadio, e.ciudad, e.fundacion, e.presupuesto, e.escudo
                          FROM equipos e
                          WHERE e.id = ?
                          """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                return resultSet.next() ? Optional.of(toEquipo(resultSet)) : Optional.empty();
            }
        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Equipo insertEquipo(Equipo equipo) throws EquiposAppException
    {

        final String SQL = """
                           INSERT INTO equipos(id                    , nombre, estadio, ciudad, fundacion, presupuesto, escudo)
                                       VALUES (nextval('seq_equipos'),   ?   ,   ?    ,    ?  ,      ?   ,      ?     ,    ?  )
                           """;
        final String[] fields = {"id"};
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields)
           )
        {
            preparedStatement.setString(1, equipo.getNombre());
            preparedStatement.setString(2, equipo.getEstadio());
            preparedStatement.setString(3, equipo.getCiudad());
            preparedStatement.setString(4, equipo.getFundacion());
            preparedStatement.setDouble(5, equipo.getPresupuesto());
            preparedStatement.setString(6, equipo.getEscudo());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                String id = resultSet.getString(1);
                return equipo.withId(id);
            }

        } catch (SQLException sqlException) {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public Optional<Equipo> updateEquipo(Equipo equipo) throws EquiposAppException
    {
        final String SQL = """
                           UPDATE equipos
                              SET nombre = ?,
                                  estadio = ?,
                                  ciudad = ?,
                                  fundacion = ?,
                                  presupuesto = ?,
                                  escudo = ?
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
            )
        {
            preparedStatement.setString(1, equipo.getNombre());
            preparedStatement.setString(2, equipo.getEstadio());
            preparedStatement.setString(3, equipo.getCiudad());
            preparedStatement.setString(4, equipo.getFundacion());
            preparedStatement.setDouble(5, equipo.getPresupuesto());
            preparedStatement.setString(6, equipo.getEscudo());
            preparedStatement.setString(7, equipo.getId());

            int count = preparedStatement.executeUpdate();
            return (count == 0) ? Optional.empty() : Optional.of(equipo);
        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }

    @Override
    public void deleteEquipoById(String id) throws EquiposAppException {
        final String SQL = """
                           DELETE 
                             FROM equipos
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new EquipoNotFoundAppException();


        } catch (SQLException sqlException)
        {
            throw toEquiposAppException(sqlException);
        }
    }


    private Equipo toEquipo(ResultSet resultSet) throws SQLException
    {
        return Equipo.builder()
                .withId(resultSet.getString(1) )
                .withNombre(resultSet.getString(2) )
                .withEstadio(resultSet.getString(3) )
                .withCiudad(resultSet.getString(4) )
                .withFundacion(resultSet.getString(5) )
                .withPresupuesto(resultSet.getDouble(6))
                .withEscudo(resultSet.getString(7) )
                .build();
    }

    private EquiposAppException toEquiposAppException(SQLException sqlException)
    {
        String message = sqlException.getMessage();
        if      ( message.contains("NN_EQUIPOS_NOMBRE")   ) return new FieldRequiredSQLException("escudo");
        else if ( message.contains("NN_EQUIPOS_ESTADIO")  ) return new FieldRequiredSQLException("estadio");
        else if ( message.contains("NN_EQUIPOS_CIUDAD")   ) return new FieldRequiredSQLException("ciudad");
        else if (message.contains("UQ_EQUIPOS_NOMBRE")    ) return new UniqueNameSQLException();
        else if ( message.contains("NN_EQUIPOS_FUNDACION")  ) return new FieldRequiredSQLException("fundacion");
        else if ( message.contains("NN_EQUIPOS_PRESUPUESTO") ) return new FieldRequiredSQLException("presupuesto");
        else if ( message.contains("NN_EQUIPOS_ESCUDO")   ) return new FieldRequiredSQLException("escudo");
        else if (message.contains("FK_CONTRATOS_EQUIPOS") ) return new ClubHasContractSQLException();

        return new SQLEquiposAppException(sqlException);
    }
}
