package model;

import model.enums.Turn;

public class
Density {
  private Dia dia;
    private Turn turno;
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
