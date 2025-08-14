package model;

import java.util.Collection;

class Node implements INode {
  public String logadouro;
  public lat-long coordinates;

  public Node(String logadouro, lat-long coordinates){
    this.logadouro = logadouro;
    this.coordinates = coordinates;
  }

  public String getLogadouro(){
    return logadouro;
  }
  public lat-long GetCoordinates(){
    return coordinates;
  }
}
