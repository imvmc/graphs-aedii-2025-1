package model;

import java.util.Collection;

class Graph implements IGraph {
  private String cityName;
  private Day day;
  private Density density;
  private Map<Node, List<Edge>> adjacencias = new HashMap<>();

@Override
  public void ignite() {
  }

  @Override
  public List<Node> getAdjacencias(Vertex v) {
    return new ArrayList<>();
  }

  @Override
  public List<Node> widthFirstSearch() {
      return new ArrayList<>();
  }

  @Override
  public List<Node> breadthFirstSearch() {
      return new ArrayList<>();
  }

  @Override
  public void addVertex(Vertex v) {
  }

  @Override
  public void addEdge(Edge e) {
  }

  public List<Edge> getAdjacenciasSimples(Node n) {
    return adjacencias.getOrDefault(n, new ArrayList<>());
  }
}
