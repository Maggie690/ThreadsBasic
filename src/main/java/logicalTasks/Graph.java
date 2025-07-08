package logicalTasks;

import java.util.*;

public class Graph {

    private Map<Integer, List<Integer>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(Integer vertex) {
        this.graph.put(vertex, new ArrayList<>());
    }

    public void addNode(Integer node, Integer newNode) {
        this.graph.get(node).add(newNode);
    }

    public void addNodes(Integer node, List<Integer> newNodes) {
        if (!graph.containsKey(node)) {
            graph.put(node, newNodes);
        } else {
            addNodeToExistingNode(node, newNodes);
            mergeNodes(node, newNodes);
        }

    }

    public Map<Integer, List<Integer>> getGraph() {
        return this.graph;
    }

    public void dfsRecursive(Graph graph, Integer node, Set<Integer> visited) {
        if (visited == null) {
            visited = new HashSet<>();
        }

        if (!visited.contains(node)) {
            visited.add(node);
            System.out.print(node + "-");

            List<Integer> neighbords = graph.getGraph().get(node);
            if (neighbords != null) {
                for (Integer neighbor : neighbords) {
                    dfsRecursive(graph, neighbor, visited);
                }
            }
        }
    }

    private void addNodeToExistingNode(Integer source, List<Integer> nodes) {
        for (var node : nodes) {
            if (graph.containsKey(node)) {
                graph.get(node).add(source);
            }
        }
    }

    private void mergeNodes(Integer node, List<Integer> newNodes) {
        graph.merge(node, Arrays.asList(new Integer[]{node}),
                (oldList, newList) -> new ArrayList<Integer>() {{
                    addAll(graph.get(node));
                    addAll(newNodes);
                }});
    }
}
