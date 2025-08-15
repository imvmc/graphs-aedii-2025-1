package model;

import java.util.Collection;

class Density {
  private Dia dia;
    private Turno turno;
    private int peopleAmount;

    public Density(Dia dia, Turno turno, int peopleAmount) {
        this.dia = dia;
        this.turno = turno;
        this.peopleAmount = peopleAmount;
    }

public Dia getDia(){
  return dia;
}
public Turno getTurno(){
  return turno;
}
  public int peopleAmount(){
    return peopleAmount;
  }
}
