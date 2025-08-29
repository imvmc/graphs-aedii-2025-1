package com.aedii.asclepius.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//melhorar quabndo o restante estiver pronto
public class Graph{
      private String city;
      private List<Node> nodes;
      private Map<Node, List<Edge>> adjacent;

    public Graph(String city) {
        this.city = city;
        this.adjacent = new HashMap<>();
    }

    public void addNode(Node node) {
    }

    public void addEdge(Node a, Node b, boolean against) {
        //adicionar adjacencia
    }

    public Collection<Node> searchPath(Node origin) {//deve usar djikstra
        return List.of();//implementar
    }



}
