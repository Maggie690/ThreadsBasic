package logicalTasks;

import java.util.*;

public class Graph<T> {

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
            addNodeOrVertex(node, newNodes);
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

    public void dfsInteractive(Graph graph, T startNode) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            T node = stack.pop();
            if (!visited.contains(node)) {
                System.out.print(node + " ");
                visited.add(node);
            }

            List<T> neighborhoods = (List<T>) graph.getGraph().get(node);
            for (T neighbor : neighborhoods) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    private void addNodeOrVertex(T source, List<T> nodes) {
        for (var node : nodes) {
            if (graph.containsKey(node)) {
                graph.get(node).add(source);
            } else {
                graph.put(node, new ArrayList<>());
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
