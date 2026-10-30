package org.example.dao;

import org.example.Model.Profesor;

import java.util.List;

public interface ProfesorDAO {

    void guardar(Profesor profesor);
    List<Profesor> listar();


}
