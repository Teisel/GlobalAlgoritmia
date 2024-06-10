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


    public Node(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        this.neighbors = new ArrayList<>();
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
        setBorder(new RoundBorder(5));

        addActionListener(new ActionListener() {
            Color initialColor = getBackground();

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton presionado");
                /*
                if ((gridGraph.getStartNode() == null) && (state != NodeState.END)) {
                    setBackground(NodeState.START.getColor());
                    state = NodeState.START;
                    //gridGraph.selectNode(Node.this);
                } else if ((gridGraph.getEndNode() == null) && (state != NodeState.START)) {
                    setBackground(NodeState.END.getColor());
                    state = NodeState.END;
                    //gridGraph.selectNode(Node.this);
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
                }*/
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

}
