package com.aedii.asclepius.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GraphParsingService {

    private final RestTemplate restTemplate;

    public GraphParsingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //retorna string para teste, devera retornar posteriormente o grafo pronto
    public String getGraphForCity(String city) {

        // chama API externa
        String url = "https://api-mapas.com/cities/" + city;
        String json = restTemplate.getForObject(url, String.class);

        // parseia JSON e cria Graph
       /* Graph graph = parseJsonToGraph(json);
        return graph;*/

        //para fim de ver a estrutura do json:
        return json;
    }

  /*  private Graph parseJsonToGraph(String json) {
        //aqui sera feito op parsing
        return new Graph();
    }*/
}
