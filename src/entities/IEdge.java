package entities;
import java.lang.reflect.Type;
import java.util.Collection;

public interface IEdge {
  public Node from;
  public Node to;
  public double distance;
  public boolean isDoubleVia;
  public Density density;

  public double CalculoPeso(){
  }

  public double GetTempo(){
  }
}
  
