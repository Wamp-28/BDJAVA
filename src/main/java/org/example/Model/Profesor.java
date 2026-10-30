package org.example.Model;

public class Profesor {

    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String profesion;

    public Profesor() {
    }

    public Profesor(String cedula, String nombre, String apellido, int edad, String profesion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.profesion = profesion;
    }

    public String getCedula() {
        return cedula;
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

    public String getProfesion() {
        return profesion;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", profesion='" + profesion + '\'' +
                '}';
    }
}
