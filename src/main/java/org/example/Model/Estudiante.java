package org.example.Model;

public class Estudiante {

    private String codigo;
    private String nombre;
    private String apellido;
    private int edad;
    private String carrera;
    private Profesor profesor;

    public Estudiante() {
    }

    public Estudiante(String codigo, String nombre, String apellido, int edad, String carrera, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.carrera = carrera;
        this.profesor = profesor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", carrera='" + carrera + '\'' +
                ", profesor=" + profesor +
                '}';
    }
}
