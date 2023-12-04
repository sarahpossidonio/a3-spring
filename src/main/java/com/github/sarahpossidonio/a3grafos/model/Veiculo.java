package com.github.sarahpossidonio.a3grafos.model;
import java.time.LocalTime;

public class Veiculo {
    private int categoria;
    private double consumoMedio;
    private double velocidadeMedia;

    public Veiculo(int categoria) {
        this.categoria = categoria;
        this.consumoMedio = obterConsumoMedio(categoria);
        this.velocidadeMedia = obterVelocidadeMedia(categoria);
    }

    public double calcularCustoCombustivel(double distancia, double precoCombustivel) {
        if (consumoMedio > 0) {
            return Math.ceil((distancia / consumoMedio) * precoCombustivel);
        } else {
            System.out.println("Categoria de veículo inválida.");
            return -1.0;
        }
    }

    public LocalTime calcularTempoDeslocamento(double distancia) {
        if (velocidadeMedia > 0) {
            double tempoHoras = distancia / velocidadeMedia;
            int horas = (int) tempoHoras;
            int minutos = (int) ((tempoHoras - horas) * 60);
            int segundos = (int) ((tempoHoras - horas - minutos / 60.0) * 3600);

            // Correção para garantir que minutos e segundos estejam no intervalo válido
            minutos = minutos % 60;
            segundos = segundos % 60;

            return LocalTime.of(horas, minutos, segundos);
        } else {
            System.out.println("Categoria de veículo inválida.");
            return null;
        }
    }


    public double calcularCustoAlimentacao(int quantidadeParada, int quantidadePessoas) {
        double custoTotalAlimentacao = 0.0;
        custoTotalAlimentacao += (20.0 * quantidadeParada * quantidadePessoas);
        return custoTotalAlimentacao;
    }

    private double obterConsumoMedio(int categoria) {
        switch (categoria) {
            case 1:
                return 30.0; // Consumo médio de uma motocicleta em km/l
            case 2:
                return 12.0; // Consumo médio de um carro em km/l
            case 3:
                return 8.0;  // Consumo médio de um micro-ônibus em km/l
            case 4:
                return 5.0;  // Consumo médio de um ônibus em km/l
            case 5:
                return 3.0;  // Consumo médio de um caminhão em km/l
            default:
                return -1.0; // Categoria de veículo inválida
        }
    }

    private double obterVelocidadeMedia(int categoria) {
        switch (categoria) {
            case 1:
                return 80.0; // Velocidade média de uma motocicleta em km/h
            case 2:
                return 100.0; // Velocidade média de um carro em km/h
            case 3:
                return 70.0;  // Velocidade média de um micro-ônibus em km/h
            case 4:
                return 60.0;  // Velocidade média de um ônibus em km/h
            case 5:
                return 50.0;  // Velocidade média de um caminhão em km/h
            default:
                return -1.0; // Categoria de veículo inválida
        }
    }


}
