package com.github.sarahpossidonio.a3grafos.model;

import java.util.List;

public class ResponsePost {

    private double distancia;
    private String tempo;
    private double combustivel;
    private Integer paradas;
    private double alimentacao;
    private List<Municipio> listaCaminho;

    public ResponsePost() {
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public double getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(double combustivel) {
        this.combustivel = combustivel;
    }

    public Integer getParadas() {
        return paradas;
    }

    public void setParadas(Integer paradas) {
        this.paradas = paradas;
    }

    public double getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(double alimentacao) {
        this.alimentacao = alimentacao;
    }
    
    public List<Municipio> getListaCaminho() {
        return listaCaminho;
    }

    public void setListaCaminho(List<Municipio> listaCaminho) {
        this.listaCaminho = listaCaminho;
    }

    
}
