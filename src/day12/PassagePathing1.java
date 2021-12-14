package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PassagePathing1 {

    public static int result = 0;

    public static void main(String[] args) {
        Graph graph = new Graph();
        initGraph(graph);
        countPaths(graph);
        System.out.println(result);
    }

    private static void initGraph(Graph graph) {
        try (BufferedReader br = new BufferedReader(new FileReader("test12.txt"))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] splitted = line.split("-");
                graph.addVertex(splitted[0]);
                graph.addVertex(splitted[1]);
                graph.addEdge(splitted[0], splitted[1]);
            }
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static void countPaths(Graph graph) {
        ArrayList<String> pathList = new ArrayList<>();
        pathList.add("start");
        countPaths("start", "end", graph, pathList);
    }

    private static void countPaths(String current, String end, Graph graph, List<String> localPathList) {
        if (current.equals(end)) {
            result++;
            return;
        }
        for (String vertex : graph.getAdjVertices(current)) {
            if (!isSmallCave(vertex) || (isSmallCave(vertex) && !localPathList.contains(vertex))) {
                localPathList.add(vertex);
                countPaths(vertex, end, graph, localPathList);
                localPathList.remove(vertex);
            }
        }
    }

    private static boolean isSmallCave(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (Character.isUpperCase(c))
                return false;
        }
        return true;
    }
}
