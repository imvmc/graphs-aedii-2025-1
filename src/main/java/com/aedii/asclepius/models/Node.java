package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node {
    private final String id;
    private final double x, y;
    private final Direction direction;
    private List<Edge> edges;

    public Node(String id, double x, double y, String direction) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
        this.direction = Direction.fromString(direction.toUpperCase());
    }

    public double distanceTo(Node other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
