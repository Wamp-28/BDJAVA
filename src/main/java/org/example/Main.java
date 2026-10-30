package org.example;

import org.example.Model.Estudiante;
import org.example.Model.Profesor;
import org.example.Util.ConexionBD;
import org.example.dao.EstudianteDAO;
import org.example.dao.EstudianteDAOImpl;
import org.example.dao.ProfesorDAO;
import org.example.dao.ProfesorDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            ProfesorDAO profesorDAO = new ProfesorDAOImpl(connection);
            EstudianteDAO estudianteDAO = new EstudianteDAOImpl(connection);

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n===== MENÚ PRINCIPAL =====");
                System.out.println("1. Registrar profesor");
                System.out.println("2. Listar profesores");
                System.out.println("3. Registrar estudiante");
                System.out.println("4. Listar estudiantes con profesor");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {

                    case 1:
                        System.out.println("\n--- REGISTRAR PROFESOR ---");

                        System.out.print("Cédula: ");
                        String cedula = scanner.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();

                        System.out.print("Edad: ");
                        int edad = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Profesión: ");
                        String profesion = scanner.nextLine();

                        Profesor profesor = new Profesor(
                                cedula,
                                nombre,
                                apellido,
                                edad,
                                profesion
                        );

                        profesorDAO.guardar(profesor);
                        System.out.println("Profesor registrado correctamente.");
                        break;

                    case 2:
                        System.out.println("\n--- LISTA DE PROFESORES ---");

                        List<Profesor> profesores = profesorDAO.listar();

                        if (profesores.isEmpty()) {
                            System.out.println("No hay profesores registrados.");
                        } else {
                            for (Profesor p : profesores) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- REGISTRAR ESTUDIANTE ---");

                        System.out.print("Código: ");
                        String codigo = scanner.nextLine();

                        System.out.print("Nombre: ");
                        String nombreEst = scanner.nextLine();

                        System.out.print("Apellido: ");
                        String apellidoEst = scanner.nextLine();

                        System.out.print("Edad: ");
                        int edadEst = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Carrera: ");
                        String carrera = scanner.nextLine();

                        System.out.print("Cédula del profesor: ");
                        String cedulaProfesor = scanner.nextLine();

                        Profesor profesorEncontrado = null;

                        for (Profesor p : profesorDAO.listar()) {
                            if (p.getCedula().equals(cedulaProfesor)) {
                                profesorEncontrado = p;
                                break;
                            }
                        }

                        if (profesorEncontrado != null) {

                            Estudiante estudiante = new Estudiante(
                                    codigo,
                                    nombreEst,
                                    apellidoEst,
                                    edadEst,
                                    carrera,
                                    profesorEncontrado
                            );

                            estudianteDAO.guardar(estudiante);
                            System.out.println("Estudiante registrado correctamente.");

                        } else {
                            System.out.println("No existe un profesor con esa cédula.");
                        }

                        break;

                    case 4:
                        System.out.println("\n--- LISTA DE ESTUDIANTES CON PROFESOR ---");

                        List<Estudiante> estudiantes = estudianteDAO.listar();

                        if (estudiantes.isEmpty()) {
                            System.out.println("No hay estudiantes registrados.");
                        } else {
                            for (Estudiante e : estudiantes) {
                                System.out.println(e);
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

            } while (opcion != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
