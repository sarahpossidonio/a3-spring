package com.github.sarahpossidonio.a3grafos.model;

public class RequestPost {

    private String saida;
    private String destino;
    private Integer veiculo;
    private double valCombustivel;
    private Integer qtdParadas;
    private Integer qtdPessoas;
    private double valAlimentacao;

    public RequestPost() {
    }
    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Integer veiculo) {
        this.veiculo = veiculo;
    }

    public double getValCombustivel() {
        return valCombustivel;
    }

    public void setValCombustivel(double valCombustivel) {
        this.valCombustivel = valCombustivel;
    }

    public Integer getQtdParadas() {
        return qtdParadas;
    }

    public void setQtdParadas(Integer qtdParadas) {
        this.qtdParadas = qtdParadas;
    }

    public Integer getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(Integer qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public double getValAlimentacao() {
        return valAlimentacao;
    }

    public void setValAlimentacao(double valAlimentacao) {
        this.valAlimentacao = valAlimentacao;
    }

}
