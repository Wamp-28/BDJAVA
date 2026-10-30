package org.example.dao;
import org.example.Model.Estudiante;
import org.example.Model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EstudianteDAOImpl implements EstudianteDAO {

    private final Connection connection;

    public EstudianteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (codigo, nombre, apellido, edad, carrera, cedula_profesor) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getCodigo());
            statement.setString(2, estudiante.getNombre());
            statement.setString(3, estudiante.getApellido());
            statement.setInt(4, estudiante.getEdad());
            statement.setString(5, estudiante.getCarrera());

            statement.setString(6, estudiante.getProfesor().getCedula());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = """
                SELECT 
                    e.codigo AS estudiante_codigo,
                    e.nombre AS estudiante_nombre,
                    e.apellido AS estudiante_apellido,
                    e.edad AS estudiante_edad,
                    e.carrera AS estudiante_carrera,
                    
                    p.cedula AS profesor_cedula,
                    p.nombre AS profesor_nombre,
                    p.apellido AS profesor_apellido,
                    p.edad AS profesor_edad,
                    p.profesion AS profesor_profesion
                    
                FROM estudiantes e
                INNER JOIN profesores p
                ON e.cedula_profesor = p.cedula
                """;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Profesor profesor = new Profesor(
                        resultSet.getString("profesor_cedula"),
                        resultSet.getString("profesor_nombre"),
                        resultSet.getString("profesor_apellido"),
                        resultSet.getInt("profesor_edad"),
                        resultSet.getString("profesor_profesion")
                );

                Estudiante estudiante = new Estudiante(
                        resultSet.getString("estudiante_codigo"),
                        resultSet.getString("estudiante_nombre"),
                        resultSet.getString("estudiante_apellido"),
                        resultSet.getInt("estudiante_edad"),
                        resultSet.getString("estudiante_carrera"), profesor
                );

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }


}
