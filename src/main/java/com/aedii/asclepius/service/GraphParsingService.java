package com.aedii.asclepius.service;

import com.aedii.asclepius.models.Edge;
import com.aedii.asclepius.models.Graph;
import com.aedii.asclepius.models.Node;
import com.aedii.asclepius.models.PreNode;
import com.aedii.asclepius.models.enums.Direction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aedii.asclepius.models.enums.Color;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphParsingService {
    private Graph graph;//grafo a armazenar
    private double tolerance = 8.0; //pixels de tolerância a desalinhamento





    public void dfs(PreNode current, List<PreNode> allNodes, List<Rectangle> blocks, Set<String> visitedEdges) {

        current.se = Color.GREY;
        for (PreNode other : allNodes) {
            if (other == current) continue;

            boolean alignedHorizontally = Math.abs(current.node.getY() - other.node.getY()) <= tolerance;
            boolean alignedVertically = Math.abs(current.node.getX() - other.node.getX()) <= tolerance;

            if ((alignedHorizontally || alignedVertically) && !intersectsBlock(current.node, other.node, blocks)) {
                String edgeId = current.node.getId() + "->" + other.node.getId();

                // Evita criar arestas duplicadas
                if (!visitedEdges.contains(edgeId)) {
                    Edge e = new Edge(current.node, other.node);
                    current.edges.add(e);
                    visitedEdges.add(edgeId);

                    // Recursão para o nó vizinho
                    if (other.color == Color.WHITE) {
                        other.father = current.node;
                        dfs(other, allNodes, blocks, visitedEdges);
                    }
                }
            }
        }

        current.color = Color.BLACK;
    }





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

    private static JsonNode readJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File("map.tmj"));
    }



    public List<PreNode> loadGraphFromJson(String jsonFile) throws Exception {
        //carrega json
        JsonNode root = readJson(jsonFile);

        List<PreNode> preNodes = loadPreNodes(root);
        List<Rectangle> blocks = loadBlocks(root);

        // Executa DFS em cada PreNode
        Set<String> visitedEdges = new HashSet<>();
        for (PreNode pn : preNodes) {
            if (pn.getColor() == Color.WHITE) {
                dfs(pn, preNodes, blocks, visitedEdges);
            }
        }

        return preNodes;
    }

    private static List<PreNode> loadPreNodes(JsonNode root) {

        List<PreNode> preNodes = new ArrayList<>();

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
                    Node node = new Node(id, x, y, Direction.fromString(dir));
                    preNodes.add(new PreNode(node));
                }
            }
        }

        return preNodes;
    }

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


