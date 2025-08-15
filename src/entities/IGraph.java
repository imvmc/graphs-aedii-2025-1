package entities;
import java.lang.reflect.Type;
import java.util.Collection;

public interface IGraph<Type> {

    void addVertex(Type data);
    Collection<IVertex> depthSearch(IVertex s); //retorna qlqr estrutura de dados com os vertices da busca
    Collection<IVertex> breadthFirstSearch(IVertex s); //retorna qlqr estrutura de dados com os vertices da busca
    //as listas de adjaCências devem ser inseridas na implementação do grafo
}
