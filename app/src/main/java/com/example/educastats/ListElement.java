package com.example.educastats;

public class ListElement {
    public String color;
    public String nombre;
    public String apellidos;
    public String grado;

    public ListElement(String color, String nombre, String apellidos, String grado) {
        this.color = color;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.grado = grado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getapellidos() {
        return apellidos;
    }

    public void setapellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getgrado() {
        return grado;
    }

    public void setgrado(String grado) {
        this.grado = grado;
    }
}