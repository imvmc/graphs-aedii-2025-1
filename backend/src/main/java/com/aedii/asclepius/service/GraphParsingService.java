package com.aedii.asclepius.service;

import com.aedii.asclepius.models.Graph;
import com.aedii.asclepius.models.Node;
import com.aedii.asclepius.models.enums.Direction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class GraphParsingService {

    /**
     * tolerância de desalinhamento de pixels
     */
    private static final double tolerance = 12.0;

    private static final String city = "Bit-A-Bit";


    /**
     * @param a nó a ser comparado
     * @param b outro nó
     * @param blocks lista de quarteirões que podem ser obstáculos na ligação
     * @return true em caso de interseção, false caso contrário
     */
    private static boolean intersectsBlock(Node a, Node b, List<Rectangle> blocks) {
        Line2D line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
        for (Rectangle block : blocks) {
            if (line.intersects(block)) return true;
        }
        return false;
    }


    /**
     * @param jsonFile caminho do arquivo json
     * @return grafo processado
     * @throws Exception caso não consiga ler o arquivo
     */
    public static Graph loadGraphFromJson(String jsonFile) throws Exception {
        //carrega json
        JsonNode root = readJson(jsonFile);

        List<Node> nodes = loadPreNodes(root);
        List<Rectangle> blocks = loadBlocks(root);

        return processAndConnectNodes(nodes, blocks);

    }

    /**
     * @param path caminho do arquivo json a serem lidos os pontos
     * @return objeto json
     * @throws IOException caso não consiga ler arquivo
     */
    private static JsonNode readJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(path));
    }

    /**
     * @param nodes lista de nós a serem processados
     * @param blocks lista de quarteirões a serem evitados nas conexões
     * @return repassa grafo processado
     */
        private static Graph processAndConnectNodes(List<Node> nodes, List<Rectangle> blocks) {

            //maps para injetar no metodo de separar nós por coincidencias horizontais e verticais
            Map<Double, List<Node>> horizontals = new HashMap<>();
            Map<Double, List<Node>> verticals = new HashMap<>();

            groupPerOrientation(horizontals, verticals, nodes);

            return connectInGraph(horizontals, verticals, blocks);

        }


    /**
     * @param horizontals injeção de dependência para armazenar map de nós alinhados horizontalmente
     * @param verticals injeção de dependência para armazenar map de nós alinhados verticalmente
     */
    private static void groupPerOrientation(Map<Double, List<Node>> horizontals, Map<Double, List<Node>> verticals, List<Node> nodes){
        for (Node node : nodes) {
            double x = node.getX();
            double y = node.getY();

            // Horizontal
            boolean addedH = false;
            for (double key : horizontals.keySet()) {
                if (Math.abs(key - y) <= tolerance) {
                    horizontals.get(key).add(node);
                    addedH = true;
                    break;
                }
            }
            if (!addedH) {
                List<Node> newLevel = new ArrayList<>();
                newLevel.add(node);
                horizontals.put(y, newLevel);
            }

            // Vertical
            boolean addedV = false;
            for (double key : verticals.keySet()) {
                if (Math.abs(key - x) <= tolerance) {
                    verticals.get(key).add(node);
                    addedV = true;
                    break;
                }
            }
            if (!addedV) {
                List<Node> newLevel = new ArrayList<>();
                newLevel.add(node);
                verticals.put(x, newLevel);
            }
        }
    }

    /**
     * @param horizontals map de nos alinhados horizontalmente
     * @param verticals map de nos alinhados verticalmente
     * @param blocks lista de quarteirões a serem evitados nas conexões
     * @return  grafo processado
     */
    private static Graph connectInGraph(Map<Double, List<Node>> horizontals, Map<Double, List<Node>> verticals, List<Rectangle> blocks) {

        Graph graph = new Graph(city);

        //realiza conexões para cada orientação
        connectHorizontally(horizontals, blocks, graph);
        connectVertically(verticals, blocks, graph);

        //retorna grafo pronto
        return graph;
    }

    /**
     * @param horizontals map de nós alinhados horizontalmente a serem conectados
     * @param blocks lista de quarteirões a serem evitados nas conexões
     * @param graph injeção do grafo qual os vertices e arestas pertencerão
     */
    private static void connectHorizontally(Map<Double, List<Node>> horizontals,
                                            List<Rectangle> blocks,
                                            Graph graph) {
        for (List<Node> level : horizontals.values()) {
            level.sort(Comparator.comparingDouble(Node::getX));
            for (int i = 0; i < level.size() - 1; i++) {
                Node a = level.get(i);
                Node b = level.get(i + 1);

                //caso não haja interseção é vizinho
                if(!intersectsBlock(a, b, blocks)){
                    //verifica se é contramão ou não
                    switch(a.getDirection()){
                        case RIGHT:
                            graph.addEdge(a, b, false);
                            graph.addEdge(b, a, true);
                        case LEFT:
                            if(b.getDirection() == Direction.LEFT){
                                graph.addEdge(a, b, true);
                                graph.addEdge(b, a, false);
                            }
                        default:
                            graph.addEdge(a, b, false);
                            graph.addEdge(b, a, b.getDirection() == Direction.RIGHT);
                    }
                }
            }
        }
    }


    /**
     * @param verticals map de nos alinhados verticalmente a serem conectados
     * @param blocks lista de quarteirões a serem evitados nas conexões
     * @param graph injeção do grafo qual os vertices e arestas pertencerão
     */
    private static void connectVertically(Map<Double, List<Node>> verticals,
                                            List<Rectangle> blocks,
                                            Graph graph){
        for (List<Node> level : verticals.values()) {
            level.sort(Comparator.comparingDouble(Node::getY));
            for (int i = 0; i < level.size() - 1; i++) {
                Node a = level.get(i);
                Node b = level.get(i + 1);

                //mesma logica
                if(!intersectsBlock(a, b, blocks)){
                    switch(a.getDirection()){
                        case UP:
                            graph.addEdge(a, b, false);
                            graph.addEdge(b, a, true);
                        case DOWN:
                            if(b.getDirection() == Direction.DOWN){
                                graph.addEdge(a, b, true);
                                graph.addEdge(b, a, false);
                            }
                        default:
                            graph.addEdge(a, b, false);
                            graph.addEdge(b, a, b.getDirection() == Direction.UP);
                    }
                }
            }
        }
    }


    /**
     * @param root objeto json a serem extraidos os dados
     * @return lista de nós preprocessados
     */
    private static List<Node> loadPreNodes(JsonNode root) {

        List<Node> nodes = new ArrayList<>();

        for (JsonNode layer : root.path("layers")) {
            if (layer.get("name").asText().equals("Points")) {
                for (JsonNode obj : layer.path("objects")) {
                    String id = obj.get("id").asText();
                    double x = obj.get("x").asDouble();
                    double y = obj.get("y").asDouble();
                    String dir = "all";

                    for (JsonNode prop : obj.get("properties")) {
                        if (prop.get("name").asText().equals("direction")) {
                            dir = prop.get("value").asText();
                        }
                    }
                    nodes.add(new Node(id, x, y, dir));
                }
            }
        }

        return nodes;
    }

    /**
     * @param root objeto json a serem extraidos os dados
     * @return lista de quarteirões a serem evitados nas conexões
     */
    private static List<Rectangle> loadBlocks(JsonNode root) {

        List<Rectangle> blocks = new ArrayList<>();

        //Pega retângulos
        for (JsonNode layer : root.path("layers")) {
            if (layer.get("name").asText().equals("Rectangles")) {
                for (JsonNode obj : layer.path("objects")) {
                    double x = obj.get("x").asDouble();
                    double y = obj.get("y").asDouble();
                    double w = obj.get("width").asDouble();
                    double h = obj.get("height").asDouble();
                    blocks.add(new Rectangle((int)x, (int)y, (int)w, (int)h));
                }
            }
        }
        return blocks;
    }
}


