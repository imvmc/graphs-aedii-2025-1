package com.aedii.asclepius.controllers;

import com.aedii.asclepius.models.Coordinate;
import com.aedii.asclepius.models.Edge;
import com.aedii.asclepius.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/graph")
public class GraphController {

    private final GraphService graphService;


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
    }
}