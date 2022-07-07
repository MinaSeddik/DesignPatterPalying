package mytrees;

public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    protected int getValue(){
        return value;
    }

    protected void setValue(int v){
        value = v;
    }

    protected Node getLeft(){
        return left;
    }

    protected Node getRight(){
        return right;
    }

    protected void setLeft(Node leftNode) {
        this.left = leftNode;
    }

    protected void setRight(Node rightNode) {
        this.right = rightNode;
    }
}
