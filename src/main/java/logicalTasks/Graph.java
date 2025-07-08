package logicalTasks;

import java.util.*;

public class Graph <T>{

    private Map<T, List<T>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(T vertex) {
        this.graph.put(vertex, new ArrayList<>());
    }

    public void addNode(T node, T newNode) {
        this.graph.get(node).add(newNode);
    }

    public void addNodes(T node, List<T> newNodes) {
        if (!graph.containsKey(node)) {
            graph.put(node, newNodes);
        } else {
            addNodeToExistingNode(node, newNodes);
            mergeNodes(node, newNodes);
        }

    }

    public Map<T, List<T>> getGraph() {
        return this.graph;
    }

    public void dfsRecursive(Graph graph, T node, Set<T> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(node)) {
            visited.add(node);
            System.out.print(node + "-");

            List<T> neighbords = (List<T>) graph.getGraph().get(node);
            if (neighbords != null) {
                for (T neighbor : neighbords) {
                    dfsRecursive(graph, neighbor, visited);
                }
            }
        }
    }

    private void addNodeToExistingNode(T source, List<T> nodes) {
        for (var node : nodes) {
            if (graph.containsKey(node)) {
                graph.get(node).add(source);
            }
        }
    }

    private void mergeNodes(T node, List<T> newNodes) {
        graph.merge(node, List.of(node),
                (oldList, newList) -> new ArrayList<T>() {{
                    addAll(graph.get(node));
                    addAll(newNodes);
                }});
    }
}
