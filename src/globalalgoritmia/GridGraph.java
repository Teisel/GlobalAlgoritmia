package globalalgoritmia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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

    void executeAlgorithm(String algorithm) {

        switch (algorithm) {
            case "A*":
                aStar();
                break;

            case "Dijkstra":
                dijkstra();
                break;
        }
    }

    private void aStar() {
        Node startNode = getStartNode();
        Node endNode = getEndNode();

        // Crear una cola de prioridad para los nodos a visitar
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return (int) (n1.getDistance(endNode) - n2.getDistance(endNode));
        });

        // Agregar el nodo de inicio a la cola
        queue.add(startNode);

        // Crear un conjunto de nodos visitados
        Set<Node> visited = new HashSet<>();

        // Crear un mapa de nodos padre
        Map<Node, Node> parentMap = new HashMap<>();

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(endNode)) {
                // Reconstruir el camino desde el nodo de fin hasta el nodo de inicio
                List<Node> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(currentNode);
                    currentNode = parentMap.get(currentNode);
                }
                // Mostrar el camino
                for (Node node : path) {
                    node.setBackground(NodeState.PATH.getColor());
                }
                return;
            }

            visited.add(currentNode);

            // Marcar el nodo actual como CHECK para visualizar que se está visitando
            currentNode.setState(NodeState.CHECK);
            currentNode.setCustomColor(NodeState.CHECK.getColor());

            // Agregar un intervalo de tiempo para que se pueda apreciar la visita de los nodos
            try {
                Thread.sleep(100); // 100 milisegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            for (Node neighbor : currentNode.getNeighbors()) {
                if (!visited.contains(neighbor) && neighbor.getState() != NodeState.GRAY) {
                    // Calcular la distancia desde el nodo de inicio hasta el nodo vecino
                    int distance = currentNode.getDistance(startNode) + 1;

                    // Calcular la distancia estimada desde el nodo vecino hasta el nodo de fin
                    int estimatedDistance = neighbor.getDistance(endNode);

                    // Calcular la prioridad del nodo vecino
                    int priority = distance + estimatedDistance;

                    // Agregar el nodo vecino a la cola con su prioridad
                    queue.add(neighbor);
                    parentMap.put(neighbor, currentNode);
                }
            }
        }

        System.out.println("No se encontró camino");
    }

    private void dijkstra() {
        System.out.println("Soy un algoritmo: dijkstra");
    }

    void reset() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node currentNode = graph.get(i).get(j);
                currentNode.setState(NodeState.WHITE);
                currentNode.setCustomColor(NodeState.WHITE.getColor());
                currentNode.setNumState(0);
                startNode = null;
                endNode = null;
            }
        }
    }

}
