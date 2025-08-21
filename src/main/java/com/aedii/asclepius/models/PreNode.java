package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Color;
import com.aedii.asclepius.models.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PreNode {
    Node node;
    Color color;
    Node father;
    List<Edge> edges;

    public PreNode(Node node) {
        this.node = node;
        this.color = null;
        this.father = null;
        this.edges = new ArrayList<>();
    }


}
