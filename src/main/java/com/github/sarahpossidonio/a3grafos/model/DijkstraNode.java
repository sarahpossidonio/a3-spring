package com.github.sarahpossidonio.a3grafos.model;
public class DijkstraNode {
    Municipio municipio;
    double distancia;

    public DijkstraNode(Municipio municipio, double distancia) {
        this.municipio = municipio;
        this.distancia = distancia;
    }
}
