
package globalalgoritmia;

import java.awt.Color;

public class NodeState {

    public static final NodeState START = new NodeState("START", new Color(93, 173, 226));
    public static final NodeState END = new NodeState("END", new Color(192, 57, 43));
    public static final NodeState GRAY = new NodeState("GRAY", new Color(86, 101, 115));
    public static final NodeState WHITE = new NodeState("WHITE", Color.WHITE);

    private String name;
    private Color color;

    private NodeState(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
