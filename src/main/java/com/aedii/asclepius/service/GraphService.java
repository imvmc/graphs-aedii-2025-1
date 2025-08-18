package com.aedii.asclepius.service;

import com.aedii.asclepius.models.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    private Graph graph;
    private final RestTemplate restTemplate;

    public GraphService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Edge> searchPath(Coordinate from, Coordinate to) {
        return new ArrayList<Edge>();
    }
}