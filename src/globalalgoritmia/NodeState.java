
package globalalgoritmia;

import java.awt.Color;

public class NodeState {

    public static final NodeState START = new NodeState("START", new Color(91, 136, 165));
    public static final NodeState END = new NodeState("END", new Color(212, 205, 197));
    public static final NodeState GRAY = new NodeState("GRAY", new Color(25, 16, 19));
    public static final NodeState WHITE = new NodeState("WHITE", new Color(244, 244, 242));
    public static final NodeState CHECK = new NodeState("CHECK", new Color(36, 58, 105));
    public static final NodeState PATH = new NodeState("PATH", new Color(69, 179, 157));

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
