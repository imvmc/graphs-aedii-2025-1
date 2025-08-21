package com.aedii.asclepius.models;

import java.util.Collection;
import java.util.List;
import java.util.Map;

//melhorar quabndo o restante estiver pronto
public class Graph{
      private String city;
      private Map<Node, List<Edge>> adj;

    public Graph(String city) {
        this.city = city;
    }

    public void addNode(Node node) {
    }

    public void addEdge(Edge e) {
    }

    public Collection<Node> searchPath(Node origin) {//deve usar djikstra
        return List.of();//implementar
    }



}
