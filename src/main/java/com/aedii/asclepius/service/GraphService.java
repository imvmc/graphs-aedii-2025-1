package com.aedii.asclepius.service;

import com.aedii.asclepius.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    private Graph graph;

    @Autowired
    private  RestTemplate restTemplate;


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

    public List<Edge> searchPath(Coordinate from, Coordinate to) {
        return new ArrayList<Edge>();
    }
}