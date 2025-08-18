package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.AreaType;
import lombok.Getter;


@Getter
public class Edge {
  private final Node from;
  private final Node to;
  private final String id;//logradouro
  private final double timeInSeconds;
  private final double distanceInMeters;
  private final AreaType areaType;

    public Edge(Node from, Node to, String id, AreaType areaType) {
        this.from = from;
        this.to = to;
        this.id = id;
        this.distanceInMeters = from.distance(to);
        this.timeInSeconds = calculateWheight();
        this.areaType = areaType;
    }

     private double calculateWheight() { //com base nos atributos, pensar depois
     // esse sera o peso com base nos outros fatores ,tempo
        return 0.0;
    }

}
  
