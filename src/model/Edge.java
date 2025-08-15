package model;

import java.util.Collection;

class Edge implements IEdge {
  private Node from;
  private Node to;
  private String idLogradouro;
  private int weightTime;
  private double distance;
  private AreaType areaType;

  public Edge(Node from, Node to, double distance, String idLogradouro, int pesoTempo, AreaType areaType) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.idLogradouro = idLogradouro;
        this.pesoTempo = pesoTempo;
        this.areaType = areaType;
    }

  @Override
    public double calculoPeso() {
        return distance;
    }

    @Override
    public double getTempo() {
        return pesoTempo;
    }

    public Node getFrom() { return from; }
    public Node getTo() { return to; }
}
  
