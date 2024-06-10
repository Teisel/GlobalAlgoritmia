package globalalgoritmia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.Border;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Global Algoritmia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        JButton button1 = new JButton("Inicio");
        JButton button2 = new JButton("Fin");
        JButton button3 = new JButton("Ver");

        button1.setBackground(new Color(93, 173, 226));
        button1.setEnabled(false);
        button1.setBorder(new RoundBorder(5));

        button2.setBackground(new Color(192, 57, 43));
        button2.setEnabled(false);
        button2.setBorder(new RoundBorder(5));

        button3.setBackground(new Color(69, 179, 157));
        button3.setBorder(new RoundBorder(5));

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        String[] options = {"A*", "Dijkstra"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        buttonPanel.add(comboBox);

        JPanel panel = new JPanel(new GridLayout(10, 10));

        frame.getContentPane().add(panel);

        GridGraph graph = new GridGraph();

        for (List<Node> row : graph.getGraph()) {
            for (Node node : row) {     
                panel.add(node);
            }
        }

        button3.addActionListener((ActionEvent e) -> {
            if (graph.getStartNode() != null && graph.getEndNode() != null) {
                String algorithm = (String) comboBox.getSelectedItem();
                //graph.executeAlgorithmAndHighlightPath(graph.getStartNode(), graph.getEndNode(), algorithm);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a start and end node.");
            }
        });

        frame.setVisible(true);

    }
}

class RoundBorder implements Border {

    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius, this.radius, this.radius, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
