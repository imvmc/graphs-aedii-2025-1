package com.aedii.asclepius.service.calculation;

public class PathCalculator {
  public static List<Edge> fasterPath(Graph graph, Node origin) {
    List<Edge> neighbors = graph.getAdjacenciasSimples(origin);
        return neighbors;
    }
}
