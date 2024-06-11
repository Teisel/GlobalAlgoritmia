package globalalgoritmia;

import java.util.ArrayList;
import java.util.List;

public class GridGraph {

    private List<List<Node>> graph;
    private Node startNode;
    private Node endNode;

    public GridGraph() {
        graph = new ArrayList<>();
        initializeGraph();
    }

    private void initializeGraph() {
        for (int i = 0; i < 10; i++) {
            List<Node> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Node node = new Node(i, j, this);
                row.add(node);
            }
            graph.add(row);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node currentNode = graph.get(i).get(j);
                if (i > 0) {
                    currentNode.addNeighbor(graph.get(i - 1).get(j));
                }
                if (j > 0) {
                    currentNode.addNeighbor(graph.get(i).get(j - 1));
                }
                if (i < 9) {
                    currentNode.addNeighbor(graph.get(i + 1).get(j));
                }
                if (j < 9) {
                    currentNode.addNeighbor(graph.get(i).get(j + 1));
                }
            }
        }
    }

    public List<List<Node>> getGraph() {
        return graph;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    void executeAlgorithm(String algorithm) 
    {
        System.out.println("Soy un algoritmo");
    }

}
