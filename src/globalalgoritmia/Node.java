package globalalgoritmia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Node extends JButton {

    private final int row;
    private final int column;
    private List<Node> neighbors;
    private NodeState state;
    private int numState;
    private GridGraph graph;

    public Node(int row, int column, GridGraph graph) {
        super();
        this.row = row;
        this.column = column;
        this.neighbors = new ArrayList<>();
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
        setBorder(new RoundBorder(5));
        this.state = NodeState.WHITE;
        this.numState = 0;
        this.graph = graph;

        addActionListener(new ActionListener() {
            Color initialColor = getBackground();

            @Override
            public void actionPerformed(ActionEvent e) {
                setColor();
            }
        });

    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }

    public int getNumState() {
        return numState;
    }

    public void setNumState(int numState) {
        this.numState = numState;
    }

    public GridGraph getGraph() {
        return graph;
    }

    public void setGraph(GridGraph graph) {
        this.graph = graph;
    }
    
    public void setCustomColor(Color color)
    {
        setBackground(color);
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColor() {
        numState++;
        switch (numState) {
            case 0:
                setBackground(NodeState.WHITE.getColor());
                state = NodeState.WHITE;
                break;

            case 1:
                if (graph.getStartNode() == null) {
                    setBackground(NodeState.START.getColor());
                    state = NodeState.START;
                    graph.setStartNode(Node.this);
                } else {
                    setColor();
                }
                break;

            case 2:
                if (state == NodeState.START) {
                    graph.setStartNode(null);
                }
                if (graph.getEndNode() == null) {
                    setBackground(NodeState.END.getColor());
                    state = NodeState.END;
                    graph.setEndNode(Node.this);
                } else {
                    setColor();
                }
                break;

            case 3:
                if (state == NodeState.END) {
                    graph.setEndNode(null);
                }
                setBackground(NodeState.GRAY.getColor());
                state = NodeState.GRAY;
                numState = -1;
                break;
        }
    }

    public int getDistance(Node node) {
        return Math.abs(this.getRow() - node.getRow()) + Math.abs(this.getColumn() - node.getColumn());
    }

    public int getDistance() {
        return getDistance(graph.getStartNode());
    }

}
