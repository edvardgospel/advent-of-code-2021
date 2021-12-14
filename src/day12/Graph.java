package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Map<String, List<String>> adjVertices = new HashMap<>();

    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void addEdge(String v1, String v2) {
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    public List<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }
}
