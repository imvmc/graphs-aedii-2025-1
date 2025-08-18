package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Day;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Graph{
  private String city;
  private Day day;
  private Density density;
  private Map<Node, List<Edge>> adj;

/*
talvez nao seja preciso (muito provavelmente)
    @Override
    public List<Node> widthFirstSearch()

    }

    @Override
    public List<Node> breadthFirstSearch()
    }
*/

    public void addNode(Node node) {
    }

    public void addEdge(Edge e) {
    }

    public Collection<Node> searchPath(Node origin) {//deve usar djikstra
        return List.of();//implementar
    }

    public Collection<Edge> pathInEdges(Collection<Node> path) {
        return List.of();
    }


}
