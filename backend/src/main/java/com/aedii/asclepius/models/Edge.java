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
        this.timeInSeconds = calculateWeight();
        this.density = Density.randomDensity();
    }
   
  private double calculateWeight() { 
    double distance = from.distanceTo(to);
    double weight = distance;

    if (density != null) {
      switch (density) {
        case LOW:
          weight *= 1.0;
          break;
        case MEDIUM:
          weight *= 1.5;
          break;
        case HIGH:
          weight *= 2.0;
          break;
        default:
          weight *= 1.0; 
      }
    }
    if (against) {
      weight *= 0.8;
    }
    return weight;
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
  
