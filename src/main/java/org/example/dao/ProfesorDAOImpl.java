package org.example.dao;
import org.example.Model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProfesorDAOImpl implements ProfesorDAO {

    private final Connection connection;

    public ProfesorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Profesor profesor) {
        String sql = "INSERT INTO profesores (cedula, nombre, apellido, edad, profesion) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, profesor.getCedula());
            statement.setString(2, profesor.getNombre());
            statement.setString(3, profesor.getApellido());
            statement.setInt(4, profesor.getEdad());
            statement.setString(5, profesor.getProfesion());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();

        String sql = "SELECT * FROM profesores";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Profesor profesor1 = new Profesor(
                        resultSet.getString("cedula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad"),
                        resultSet.getString("profesion")
                );

                profesores.add(profesor1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return profesores;
    }


}
