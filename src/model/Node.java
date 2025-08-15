package model;

import java.util.Collection;

class Node implements INode {
  public String logradouro;
  public lat-long coordinates;

  public Node(String logradouro, lat-long coordinates){
    this.logradouro = logradouro;
    this.coordinates = coordinates;
  }

  public String getLogradouro(){
    return logradouro;
  }
  public lat-long GetCoordinates(){
    return coordinates;
  }
}
