package model;

import java.util.Collection;

class Edge implements IEdge {
  private Node from;
  private Node to;
  private String streetId;
  private int baseTime;
  private double distance;
  private AreaType areaType;

  public Edge(Node from, Node to, String streetId, int baseTime, AreaType areaType) {
      this.from = from;
      this.to = to;
      this.streetId = streetId;
      this.baseTime = baseTime;
      this.areaType = areaType;
      this.distance = Haversine.calculate(from.getCoordinates(), to.getCoordinates());
    }
  
  @Override
  public double calculateWeight() {
    return distance;
  }

  @Override
  public double getTime() {
    return baseTime;
  }

  public Node getFrom() { return from; }
  public Node getTo() { return to; }
  public String getStreetId() { return streetId; }
  public int getBaseTime() { return baseTime; }
  public double getDistance() { return distance; }
  public AreaType getAreaType() { return areaType; }
}
  
