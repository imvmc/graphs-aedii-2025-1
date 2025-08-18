package com.aedii.asclepius.controllers;

import com.aedii.asclepius.models.Coordinate;
import com.aedii.asclepius.models.Edge;
import com.aedii.asclepius.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;

    @GetMapping
    public String getGraphForCity(String city) {

        String url = "https://api-mapas.com/cities/" + city;

        try {
            String json = restTemplate.getForObject(url, String.class);
            return json;
        } catch (Exception e) {
            // loga o erro
            System.err.println("Erro ao acessar API externa: " + e.getMessage());
            // retorna mensagem amigável ou JSON de erro
            return "{\"error\": \"Não foi possível acessar a API externa.\"}";
        }
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