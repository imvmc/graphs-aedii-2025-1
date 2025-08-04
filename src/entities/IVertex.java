package entities;

import java.lang.reflect.Type;
import java.util.Collection;

public interface IVertex{
    Type data;
    Collection<IVertex> neighbours;

}
