package com.github.sarahpossidonio.a3grafos.model;
import java.util.*;

public class GrafoPonderado {
    private Map<Municipio, Map<Municipio, Double>> listaAdjacencia;

    public GrafoPonderado() {
        this.listaAdjacencia = new HashMap<>();
    }

    public void adicionarMunicipio(Municipio municipio) {
        listaAdjacencia.put(municipio, new HashMap<>());
    }

    public void adicionarAresta(Municipio municipio1, Municipio municipio2) {
        verificarExistenciaMunicipio(municipio1);
        verificarExistenciaMunicipio(municipio2);

        //calcula o peso com base no calculo da coordenadas
        double peso = calcularPeso(municipio1, municipio2);
        listaAdjacencia.get(municipio1).put(municipio2, peso);
        listaAdjacencia.get(municipio2).put(municipio1, peso);
    }

    private void verificarExistenciaMunicipio(Municipio municipio) {
        if (!listaAdjacencia.containsKey(municipio)) {
            throw new IllegalArgumentException(municipio + " deve ser adicionada ao grafo antes de adicionar uma aresta.");
        }
    }

    //formula de haversine para cálculo da diferença das coordenadas.
    public double calcularPeso(Municipio cidade1, Municipio cidade2) {
        // Raio da Terra em quilômetros
        final double raioTerra = 6371.0;

        // Coordenadas em radianos
        double lat1 = Math.toRadians(cidade1.getLatitude());
        double lon1 = Math.toRadians(cidade1.getLongitude());
        double lat2 = Math.toRadians(cidade2.getLatitude());
        double lon2 = Math.toRadians(cidade2.getLongitude());

        // Diferença entre as latitudes e longitudes
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distância em quilômetros
        double distancia = raioTerra * c;

        return Math.ceil(distancia);
    }

    public double calcularDistanciaTotal(List<Municipio> path) {
        double totalDistance = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            Municipio currentCity = path.get(i);
            Municipio nextCity = path.get(i + 1);

            double distance = listaAdjacencia.get(currentCity).get(nextCity);
            totalDistance += distance;
        }

      return Math.ceil(totalDistance);
    }

    public List<Municipio> dijkstra(Municipio inicio, Municipio fim) {
        PriorityQueue<DijkstraNode> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distancia));
        Map<Municipio, Double> distancias = new HashMap<>();
        Map<Municipio, Municipio> mapaPai = new HashMap<>();

        // Inicialização
        for (Municipio municipio : listaAdjacencia.keySet()) {
            distancias.put(municipio, Double.POSITIVE_INFINITY);
        }
        distancias.put(inicio, 0.0);

        filaPrioridade.add(new DijkstraNode(inicio, 0.0));

        while (!filaPrioridade.isEmpty()) {
            DijkstraNode atualNode = filaPrioridade.poll();
            Municipio atual = atualNode.municipio;

            for (Map.Entry<Municipio, Double> vizinho : listaAdjacencia.get(atual).entrySet()) {
                Municipio proximaMunicipio = vizinho.getKey();
                double novaDistancia = distancias.get(atual) + vizinho.getValue();

                if (novaDistancia < distancias.get(proximaMunicipio)) {
                    distancias.put(proximaMunicipio, novaDistancia);
                    mapaPai.put(proximaMunicipio, atual);
                    filaPrioridade.add(new DijkstraNode(proximaMunicipio, novaDistancia));
                }
            }
        }

        return construirCaminhoDijkstra(mapaPai, fim);
    }

    private List<Municipio> construirCaminhoDijkstra(Map<Municipio, Municipio> mapaPai, Municipio fim) {
        List<Municipio> caminho = new ArrayList<>();
        Municipio atual = fim;

        while (atual != null) {
            caminho.add(atual);
            atual = mapaPai.get(atual);
        }

        Collections.reverse(caminho);
        return caminho;
    }

    public Municipio encontrarMunicipioPorNome(String nomeMunicipio) {
        for (Municipio municipio : listaAdjacencia.keySet()) {
            if (municipio.getNome().equalsIgnoreCase(nomeMunicipio)) {
                return municipio;
            }
        }
        return null;
    }
}
