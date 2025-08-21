package com.aedii.asclepius.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.*;

class Node {
    int id;
    double x, y;
    String direction;
    List<Edge> edges = new ArrayList<>();

    public Node(int id, double x, double y, String direction) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    void addEdge(Edge e) {
        edges.add(e);
    }
}

class Edge {
    Node from, to;
    double weight;

    public Edge(Node from, Node to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class GraphDFS {

    private static boolean intersectsBlock(Node a, Node b, List<Rectangle> blocks) {
        Line2D line = new Line2D.Double(a.x, a.y, b.x, b.y);
        for (Rectangle block : blocks) {
            if (line.intersects(block)) return true;
        }
        return false;
    }

    private static void dfs(Node current,List<Node> allNodes, List<Rectangle> blocks, double tolerance, Set<String> visitedEdges,
                            Set<Node> visitedNodes) {

        visitedNodes.add(current);

        for (Node other : allNodes) {
            if (other == current) continue;

            boolean alignedHorizontally = Math.abs(current.y - other.y) <= tolerance;
            boolean alignedVertically = Math.abs(current.x - other.x) <= tolerance;

            if ((alignedHorizontally || alignedVertically) &&
                    !intersectsBlock(current, other, blocks)) {

                String edgeId1 = current.id + "-" + other.id;
                String edgeId2 = other.id + "-" + current.id;

                if (!visitedEdges.contains(edgeId1) && !visitedEdges.contains(edgeId2)) {
                    // peso: distância * tráfego (aqui tráfego = 1.0, mas pode vir do JSON)
                    double distance = Math.hypot(current.x - other.x, current.y - other.y);
                    double weight = distance * 1.0;

                    Edge e1 = new Edge(current, other, weight);
                    Edge e2 = new Edge(other, current, weight); // grafo bidirecional
                    current.addEdge(e1);
                    other.addEdge(e2);

                    visitedEdges.add(edgeId1);
                    visitedEdges.add(edgeId2);

                    if (!visitedNodes.contains(other)) {
                        dfs(other, allNodes, blocks, tolerance, visitedEdges, visitedNodes);
                    }
                }
            }
        }
    }

    /*public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("map.tmj")); // seu JSON exportado

        List<Node> nodes = new ArrayList<>();
        List<Rectangle> blocks = new ArrayList<>();

        // Lê nós
        for (JsonNode layer : root.path("layers")) {
            if (layer.get("name").asText().equals("Points")) {
                for (JsonNode obj : layer.path("objects")) {
                    int id = obj.get("id").asInt();
                    double x = obj.get("x").asDouble();
                    double y = obj.get("y").asDouble();
                    String direction = "all";
                    if (obj.has("properties")) {
                        for (JsonNode prop : obj.get("properties")) {
                            if (prop.get("name").asText().equals("direction")) {
                                direction = prop.get("value").asText();
                            }
                        }
                    }
                    nodes.add(new Node(id, x, y, direction));
                }
            }
        }

        // Lê retângulos
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

        // Executa DFS a partir de cada nó
        Set<String> visitedEdges = new HashSet<>();
        Set<Node> visitedNodes = new HashSet<>();
        double tolerance = 5;

        for (Node n : nodes) {
            if (!visitedNodes.contains(n)) {
                dfs(n, nodes, blocks, tolerance, visitedEdges, visitedNodes);
            }
        }

        // Mostra grafo
        for (Node n : nodes) {
            System.out.print("Node " + n.id + " -> ");
            for (Edge e : n.edges) {
                System.out.print(e.to.id + "(w=" + e.weight + ") ");
            }
            System.out.println();
        }
    }
}*/