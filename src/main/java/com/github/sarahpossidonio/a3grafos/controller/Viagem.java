package com.github.sarahpossidonio.a3grafos.controller;

import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sarahpossidonio.a3grafos.model.GrafoPonderado;
import com.github.sarahpossidonio.a3grafos.model.Municipio;
import com.github.sarahpossidonio.a3grafos.model.RequestPost;
import com.github.sarahpossidonio.a3grafos.model.ResponsePost;
import com.github.sarahpossidonio.a3grafos.model.Veiculo;

@RestController
@RequestMapping("/api")
public class Viagem {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/teste")
    public String ola(){
        return "Tudo Certo";
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/calcular")
    public ResponseEntity<ResponsePost> calcular(@RequestBody RequestPost post){

        GrafoPonderado grafo = montarGrafo();

        Veiculo veiculo = new Veiculo(post.getVeiculo());

        Municipio citOrigem = grafo.encontrarMunicipioPorNome(post.getSaida());
        Municipio citDestino = grafo.encontrarMunicipioPorNome(post.getDestino());
        List<Municipio> menorCaminho = grafo.dijkstra(citOrigem, citDestino);

        double distancia = grafo.calcularDistanciaTotal(menorCaminho);
        double custoCombustivel = veiculo.calcularCustoCombustivel(distancia, post.getValCombustivel());

        LocalTime tempoDeslocamento = veiculo.calcularTempoDeslocamento(distancia);

        double custoComTaxa = veiculo.calcularCustoAlimentacao(post.getQtdParadas(), post.getQtdPessoas(), post.getValAlimentacao());

        ResponsePost res = new ResponsePost();

        res.setDistancia(distancia);
        res.setCombustivel(custoCombustivel);
        res.setAlimentacao(custoComTaxa);
        res.setParadas(post.getQtdParadas());
        String hora = tempoDeslocamento.getHour()<10?"0"+tempoDeslocamento.getHour():tempoDeslocamento.getHour()+"";
        String min = tempoDeslocamento.getMinute()<10?"0"+tempoDeslocamento.getMinute():tempoDeslocamento.getMinute()+"";
        String seg = tempoDeslocamento.getSecond()<10?"0"+tempoDeslocamento.getSecond():tempoDeslocamento.getSecond()+"";
        res.setTempo(hora+":"+min+":"+seg);
        res.setListaCaminho(menorCaminho);

        return ResponseEntity.ok(res);
    }

    public GrafoPonderado montarGrafo(){
        
		GrafoPonderado grafo = new GrafoPonderado();
        Municipio alegrete = new Municipio("Alegrete", -29.7848016, -55.775657);
        Municipio barraDoQuarai = new Municipio("Barra do Quaraí", -30.206994, -57.5246268);
        Municipio garruchos = new Municipio("Garruchos", -28.194889, -55.639278);
        Municipio itaqui = new Municipio("Itaqui", -29.1525115, - -56.5507434);
        Municipio macambara = new Municipio("Maçambará", -29.1482364, -56.0640154);
        Municipio manoelViana = new Municipio("Manoel Viana", -29.5938305, -55.4811322);
        Municipio quarai = new Municipio("Quaraí", -30.359320, -56.449427);
        Municipio saoBorja = new Municipio("São Borja", -28.680582, -55.9780875);
        Municipio saoFranciscoDeAssis = new Municipio("São Francisco de Assis", -29.605679, -55.156422);
        Municipio uruguaiana = new Municipio("Uruguaiana", -29.771519, -57.079754);
        Municipio rosarioDoSul = new Municipio("Rosário do Sul", -30.244349, -54.921744);
        Municipio santaMargaridaDoSul = new Municipio("Santa Margarida do Sul", -30.3351584, -54.0810373);
        Municipio santanaDoLivramento = new Municipio("Sant'Ana do Livramento", -30.8897503, -55.5322795);
        Municipio saoGabriel = new Municipio("São Gabriel", -30.334730, -54.348598);
        Municipio acegua = new Municipio("Aceguá", -31.8616825, -54.1669653);
        Municipio bage = new Municipio("Bagé", -31.328502, -54.1072699);
        Municipio domPedrito = new Municipio("Dom Pedrito", -30.981847, -54.6774686);
        Municipio hulhaNegra = new Municipio("Hulha Negra", -31.416101, -53.868496);
        Municipio lavrasDoSul = new Municipio("Lavras do Sul", -30.840507, -53.927787);

        grafo.adicionarMunicipio(alegrete);
        grafo.adicionarMunicipio(barraDoQuarai);
        grafo.adicionarMunicipio(garruchos);
        grafo.adicionarMunicipio(itaqui);
        grafo.adicionarMunicipio(macambara);
        grafo.adicionarMunicipio(manoelViana);
        grafo.adicionarMunicipio(quarai);
        grafo.adicionarMunicipio(saoBorja);
        grafo.adicionarMunicipio(saoFranciscoDeAssis);
        grafo.adicionarMunicipio(uruguaiana);
        grafo.adicionarMunicipio(rosarioDoSul);
        grafo.adicionarMunicipio(santaMargaridaDoSul);
        grafo.adicionarMunicipio(santanaDoLivramento);
        grafo.adicionarMunicipio(saoGabriel);
        grafo.adicionarMunicipio(acegua);
        grafo.adicionarMunicipio(bage);
        grafo.adicionarMunicipio(domPedrito);
        grafo.adicionarMunicipio(hulhaNegra);
        grafo.adicionarMunicipio(lavrasDoSul);

        grafo.adicionarAresta(alegrete, itaqui);
        grafo.adicionarAresta(alegrete, manoelViana);
        grafo.adicionarAresta(alegrete, rosarioDoSul);
        grafo.adicionarAresta(alegrete, quarai);
        grafo.adicionarAresta(alegrete, uruguaiana);

        grafo.adicionarAresta(barraDoQuarai, uruguaiana);

        grafo.adicionarAresta(garruchos, saoBorja);
        grafo.adicionarAresta(garruchos, uruguaiana);

        grafo.adicionarAresta(itaqui, alegrete);
        grafo.adicionarAresta(itaqui, macambara);
        grafo.adicionarAresta(itaqui, saoBorja);
        grafo.adicionarAresta(itaqui, manoelViana);
        grafo.adicionarAresta(itaqui, uruguaiana);

        grafo.adicionarAresta(macambara, itaqui);
        grafo.adicionarAresta(macambara, saoBorja);
        grafo.adicionarAresta(macambara, manoelViana);

        grafo.adicionarAresta(manoelViana, alegrete);
        grafo.adicionarAresta(manoelViana, itaqui);
        grafo.adicionarAresta(manoelViana, macambara);
        grafo.adicionarAresta(manoelViana, saoFranciscoDeAssis);

        grafo.adicionarAresta(quarai, alegrete);
        grafo.adicionarAresta(quarai, santanaDoLivramento);
        grafo.adicionarAresta(quarai, uruguaiana);

        grafo.adicionarAresta(saoBorja, garruchos);
        grafo.adicionarAresta(saoBorja, macambara);
        grafo.adicionarAresta(saoBorja, itaqui);

        grafo.adicionarAresta(saoFranciscoDeAssis, manoelViana);
        grafo.adicionarAresta(saoFranciscoDeAssis, alegrete);
        grafo.adicionarAresta(saoFranciscoDeAssis, macambara);

        grafo.adicionarAresta(uruguaiana, alegrete);
        grafo.adicionarAresta(uruguaiana, barraDoQuarai);
        grafo.adicionarAresta(uruguaiana, itaqui);
        grafo.adicionarAresta(uruguaiana, quarai);

        grafo.adicionarAresta(rosarioDoSul, alegrete);
        grafo.adicionarAresta(rosarioDoSul, santanaDoLivramento);
        grafo.adicionarAresta(rosarioDoSul, saoGabriel);
        grafo.adicionarAresta(rosarioDoSul, quarai);
        grafo.adicionarAresta(rosarioDoSul, domPedrito);

        grafo.adicionarAresta(santaMargaridaDoSul, lavrasDoSul);
        grafo.adicionarAresta(santaMargaridaDoSul, saoGabriel);

        grafo.adicionarAresta(santanaDoLivramento, quarai);
        grafo.adicionarAresta(santanaDoLivramento, rosarioDoSul);
        grafo.adicionarAresta(santanaDoLivramento, domPedrito);

        grafo.adicionarAresta(saoGabriel, rosarioDoSul);
        grafo.adicionarAresta(saoGabriel, santaMargaridaDoSul);
        grafo.adicionarAresta(saoGabriel, lavrasDoSul);
        grafo.adicionarAresta(saoGabriel, domPedrito);

        grafo.adicionarAresta(acegua, bage);
        grafo.adicionarAresta(acegua, hulhaNegra);

        grafo.adicionarAresta(bage, acegua);
        grafo.adicionarAresta(bage, lavrasDoSul);
        grafo.adicionarAresta(bage, domPedrito);
        grafo.adicionarAresta(bage, hulhaNegra);

        grafo.adicionarAresta(hulhaNegra, acegua);
        grafo.adicionarAresta(hulhaNegra, bage);

        grafo.adicionarAresta(lavrasDoSul, bage);
        grafo.adicionarAresta(lavrasDoSul, domPedrito);
        grafo.adicionarAresta(lavrasDoSul, saoGabriel);
        grafo.adicionarAresta(lavrasDoSul, santaMargaridaDoSul);

        return grafo;
    }
}
