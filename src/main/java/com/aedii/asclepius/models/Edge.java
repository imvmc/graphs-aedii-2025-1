package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Density;
import lombok.Getter;


@Getter
public class Edge {
  private final Node from;
  private final Node to;
  private final double timeInSeconds;
  private final Density density;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        this.timeInSeconds = calculateWheight();
        this.density = Density.randomDensity();
    }

     private double calculateWheight() { //com base nos atributos, pensar depois
     // esse sera o peso com base na distancia de pixels e peso da densidade de trafego
        return 0.0;
    }

}
  
