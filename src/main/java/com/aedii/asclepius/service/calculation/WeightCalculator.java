package com.aedii.asclepius.service.calculation;

public class WeightCalculator {

  public static double calculate(Edge e, Density d) {
    double factor = 1.0;
    if (d != null) {
      factor += d.getPeopleAmount() / 1000.0;
    }
    return e.getDistance() * fator;
  }
}
