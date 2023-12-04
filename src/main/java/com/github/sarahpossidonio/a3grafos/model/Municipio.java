package com.github.sarahpossidonio.a3grafos.model;
public class Municipio {
    private String nome;
    private double latitude;
    private double longitude;

    public Municipio(String name, double latitude, double longitude) {
        this.nome = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return nome;
    }
}
