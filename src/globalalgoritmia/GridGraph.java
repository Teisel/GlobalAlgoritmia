package globalalgoritmia;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

    public void selectNode(Node node) {
        if (node.getState() == NodeState.START) {
            startNode = node;
        } else if (node.getState() == NodeState.END) {
            endNode = node;
        }

    }

    public void executeAlgorithmAndHighlightPath(Node startNode, Node endNode, String algorithm) {
        List<Node> path = new ArrayList<>();
        if (algorithm.equals("A*")) {
            path = aStarSearch(startNode, endNode);
        } else if (algorithm.equals("Dijkstra")) {
            path = dijkstraAlgorithm(startNode, endNode);
        }

        if (!path.isEmpty()) {
            Color shortestPathColor = new Color(255, 230, 0); 
            Color visitedColor = new Color(128, 0, 128); 

            for (Node node : path) {
                if (node.getState() == NodeState.GRAY) {
                    visitNode(node, visitedColor);
                }
            }

            for (Node node : path) {
                node.setColor(shortestPathColor);
            }
        }
    }

    private List<Node> aStarSearch(Node startNode, Node endNode) {
    startNode.setState(NodeState.GRAY);
    endNode.setState(NodeState.GRAY);

    PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.getF(endNode)));
    openList.add(startNode);

    while (!openList.isEmpty()) {
        Node currentNode = openList.poll();

        if (currentNode == endNode) {
            return buildPath(currentNode);
        }

        for (Node neighbor : currentNode.getNeighbors()) {
            if (neighbor.getState() == NodeState.WHITE) {
                int tentativeG = currentNode.getG() + 1;
                if (tentativeG < neighbor.getG()) {
                    neighbor.setG(tentativeG);
                    neighbor.setH(neighbor.getH(endNode));
                    neighbor.setParent(currentNode);
                    openList.removeIf(n -> n == neighbor);
                    openList.add(neighbor);
                }
            }
        }
    }

    return new ArrayList<>();
}

    public int calculateH(Node node, Node endNode) {
        return Math.abs(node.getRow() - endNode.getRow()) + Math.abs(node.getColumn() - endNode.getColumn());
    }

    private List<Node> buildPath(Node node) {
    List<Node> path = new ArrayList<>();
    while (node != null) {
        path.add(node);
        node = node.getParent();
    }
    Collections.reverse(path);
    return path;
}

    private List<Node> dijkstraAlgorithm(Node startNode, Node endNode) {
        startNode.setState(NodeState.GRAY);
        endNode.setState(NodeState.GRAY);

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.getG()));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (currentNode == endNode) {
                return buildPath(currentNode);
            }

            for (Node neighbor : currentNode.getNeighbors()) {
                if (neighbor.getState() == NodeState.WHITE) {
                    int tentativeG = currentNode.getG() + 1;
                    if (tentativeG < neighbor.getG()) {
                        neighbor.setG(tentativeG);
                        neighbor.setParent(currentNode);
                        openList.removeIf(n -> n == neighbor);
                        openList.add(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>();
    }
    
    

    public void visitNode(Node node, Color color) {
        node.setColor(color);
    }

    public void clearNodes() {
        for (List<Node> row : graph) {
            for (Node node : row) {
                node.setState(NodeState.WHITE);
                node.setColor(Color.WHITE);
            }
        }
        startNode = null;
        endNode = null;
    }

}
