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
    private GridGraph gridGraph;
    private boolean isStart;
    private boolean isEnd;
    private Node parent;
    private int g;
    private int h;

    public Node(int row, int column, GridGraph gridGraph) {
        super();
        this.row = row;
        this.column = column;
        this.neighbors = new ArrayList<>();
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
        setBorder(new RoundBorder(5));
        state = NodeState.WHITE;
        this.gridGraph = gridGraph;
        this.isStart = false;
        this.isEnd = false;
        this.parent = null;
        this.g = 0;
        this.h = 0;
        setFocusable(true);
        requestFocusInWindow();
        setEnabled(true);

        addActionListener(new ActionListener() {
            Color initialColor = getBackground();

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton presionado");
                if ((gridGraph.getStartNode() == null) && (state != NodeState.END)) {
                    setBackground(NodeState.START.getColor());
                    state = NodeState.START;
                    gridGraph.selectNode(Node.this);
                } else if ((gridGraph.getEndNode() == null) && (state != NodeState.START)) {
                    setBackground(NodeState.END.getColor());
                    state = NodeState.END;
                    gridGraph.selectNode(Node.this);
                } else if (state == NodeState.WHITE) {
                    setBackground(NodeState.GRAY.getColor());
                    state = NodeState.GRAY;
                } else if (state == NodeState.GRAY) {
                    setBackground(NodeState.WHITE.getColor());
                    state = NodeState.WHITE;
                } else if (state == NodeState.START) {
                    gridGraph.setStartNode(null);
                    state = NodeState.WHITE;
                    setBackground(NodeState.WHITE.getColor());
                } else if (state == NodeState.END) {
                    gridGraph.setEndNode(null);
                    state = NodeState.WHITE;
                    setBackground(NodeState.WHITE.getColor());
                }
            }
        });
        
        

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

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void addNeighbors(List<Node> nodes) {
        for (Node node : nodes) {
            if (node.getRow() == row - 1) {
                addNeighbor(node);
            } else if (node.getRow() == row + 1) {
                addNeighbor(node);
            } else if (node.getColumn() == column - 1) {
                addNeighbor(node);
            } else if (node.getColumn() == column + 1) {
                addNeighbor(node);
            }
        }
    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }

    public void setColor(Color color) {
        setBackground(color);
    }

    public int getG() {
        return (parent != null) ? parent.getG() + 1 : 0;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getH(Node endNode) {
        return gridGraph.calculateH(this, endNode);
    }

    public int getF(Node endNode) {
        return getG() + getH(endNode);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
