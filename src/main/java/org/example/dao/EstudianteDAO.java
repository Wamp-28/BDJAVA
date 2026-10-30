package org.example.dao;

import org.example.Model.Estudiante;

import java.util.List;

public interface EstudianteDAO {

    void guardar(Estudiante estudiante);
    List<Estudiante> listar();
    //void eliminar(String codigo);
}
