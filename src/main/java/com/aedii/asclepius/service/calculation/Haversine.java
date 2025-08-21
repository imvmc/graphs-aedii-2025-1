package com.aedii.asclepius.service.calculation;

public class Haversine {
  private static final double EARTH_RADIUS = 6371.0;

  public static double calculate(String coord1, String coord2) {
    String[] c1 = coord1.split(",");
    String[] c2 = coord2.split(",");

    double lat1 = Double.parseDouble(c1[0]);
    double lon1 = Double.parseDouble(c1[1]);
    double lat2 = Double.parseDouble(c2[0]);
    double lon2 = Double.parseDouble(c2[1]);

    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);

    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(dLon / 2), 2);

    double c = 2 * Math.asin(Math.sqrt(a));

    return EARTH_RADIUS * c;
  }
}
