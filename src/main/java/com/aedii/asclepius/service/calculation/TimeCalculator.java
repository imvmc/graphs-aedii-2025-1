package com.aedii.asclepius.service.calculation;

public class TimeCalculator {
  public static double calculate(Edge e, double kmSpeed) {
        double distanceKm = e.getDistance();
        double hourTime = distanceKm / kmSpeed;
        return hourTime * 3600.0;
    }
}
