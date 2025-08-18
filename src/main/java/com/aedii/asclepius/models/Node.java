package com.aedii.asclepius.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//abaixo, isso cria tudo sem precisar fazer o codigo
@Getter
@Setter
@AllArgsConstructor
class Node{
    private String id;
    private Coordinate coordinate;


    public double distance(Node b) {
        //logica
        return 0;
    }
}
