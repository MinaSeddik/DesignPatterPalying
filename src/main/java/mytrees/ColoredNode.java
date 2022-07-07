package mytrees;

public class ColoredNode extends Node {

    private Node parent;
    private NodeColor nodeColor;

    public ColoredNode(int value) {
        super(value);
        nodeColor = NodeColor.RED;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node prt){
        parent = prt;
    }

    public NodeColor getColor(){
        return nodeColor;
    }

    public void setColor(NodeColor color){
        nodeColor = color;
    }

}
