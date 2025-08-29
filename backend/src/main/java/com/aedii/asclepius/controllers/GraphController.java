package com.aedii.asclepius.controllers;

import com.aedii.asclepius.models.Coordinate;
import com.aedii.asclepius.models.Edge;
import com.aedii.asclepius.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{city}")
    public String getGraphForCity(@PathVariable String city) {
        String result;

        String url = "https://overpass-api.de/api/interpreter?data="
                + "[out:json];"
                + "area[\"name\"=\"" + city + "\"][\"boundary\"=\"administrative\"]->.a;"
                + "way(area.a)[\"highway\"];"
                + "out geom;";

        try {
            result = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            result = "{\"error\":\"Falha ao acessar API\"}";
        }

        return result;
    }

/*
    //exemplo do chatgpt de fluxo, analizar e testar
    @PostMapping("/path")
    public List<Edge> getPath(@RequestBody Map<String, Object> body) {

        //antes, verificar se a cidade já está carregada ,do contrario, carrega

        Map<String, Object> fromMap = (Map<String, Object>) body.get("from");
        Map<String, Object> toMap   = (Map<String, Object>) body.get("to");

        //so precisa pegar o map double mesmo
        Coordinate from = new Coordinate((Double) fromMap.get("lat"), (Double) fromMap.get("lng"));
        Coordinate to   = new Coordinate((Double) toMap.get("lat"), (Double) toMap.get("lng"));


        return graphService.searchPath(from, to); // já retorna List<Edge>
    }*/
}