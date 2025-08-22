package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Density;
import lombok.Getter;


@Getter
public class Edge {
  private final Node from;
  private final Node to;
  private final double timeInSeconds;
  private final Density density;
  private final boolean against;

    public Edge(Node from, Node to, boolean against) {
        this.from = from;
        this.to = to;
        this.against = against;
        this.timeInSeconds = calculateWheight();
        this.density = Density.randomDensity();
    }

     private double calculateWheight() { //com base nos atributos, pensar depois
     // esse sera o peso com base na distancia de pixels e peso da densidade de trafego
       double distance = from.distanceTo(to);
       this.timeInSeconds = WeightCalculator.calculate(distance, density);
       if (against) {
         this.timeInSeconds *= 0.8;
       }
    }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("from", from.getId());
    json.put("to", to.getId());
    json.put("density", density.toString());
    json.put("against", against);
    json.put("timeInSeconds", timeInSeconds);
    return json;
    }

}
  
