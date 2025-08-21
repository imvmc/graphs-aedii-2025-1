package com.aedii.asclepius.models;

import com.aedii.asclepius.models.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//abaixo, isso cria tudo sem precisar fazer o codigo
@Getter
@Setter
@AllArgsConstructor
public class Node{
    private String id;
    private double x, y;
    private Direction direction;

    public double distance(Node b) {

        //calculo
        return 0.0;
    }
}
