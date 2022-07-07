package mytrees;

public class RedBlackTree {

    private Node root;

    // these are some flags.
    // Respective rotations are performed during traceback.
    // rotations are done if flags are true.
    private boolean leftLeft = false;
    private boolean rightRight = false;
    private boolean leftRight = false;
    private boolean rightLeft = false;


    public void insert(int value) {
        if (root == null) {
            root = new ColoredNode(value);
            ((ColoredNode) root).setColor(NodeColor.BLACK);
        } else {
            root = insert(root, value);
        }

    }

    private ColoredNode insert(Node node, int value) {

        // redRedConflict is true when RED RED conflict is there.
        boolean redRedConflict = false;

        if (node == null) {
            return new ColoredNode(value);
        } else if (value < node.getValue()) {
            ColoredNode leftNode = insert(node.getLeft(), value);
            node.setLeft(leftNode);
            leftNode.setParent(node);

            if (root != node) {
                // check for RED RED conflict
                if (((ColoredNode) node).getColor() == NodeColor.RED && leftNode.getColor() == NodeColor.RED) {
                    redRedConflict = true;
                }
            }
        } else {
            ColoredNode rightNode = insert(node.getRight(), value);
            node.setRight(rightNode);
            rightNode.setParent(node);

            if (root != node) {
                // check for RED RED conflict
                if (((ColoredNode) node).getColor() == NodeColor.RED && rightNode.getColor() == NodeColor.RED) {
                    redRedConflict = true;
                }
            }
        }


        // now lets rotate.
        if (this.leftLeft) // for left rotate.
        {
            node = rotateLeft(node);
            ((ColoredNode) node).setColor(NodeColor.BLACK);
            ((ColoredNode) (node.getLeft())).setColor(NodeColor.RED);
            leftLeft = false;
        } else if (this.rightRight) // for right rotate
        {
            node = rotateRight(node);
            ((ColoredNode) node).setColor(NodeColor.BLACK);
            ((ColoredNode) (node.getRight())).setColor(NodeColor.RED);
            rightRight = false;
        } else if (this.rightLeft)  // for right and then left
        {
            Node rightNode = rotateRight(node.getRight());
            node.setRight(rightNode);
            ((ColoredNode)node.getRight()).setParent(node);
            node = rotateLeft(node);
            ((ColoredNode) node).setColor(NodeColor.BLACK);
            ((ColoredNode) (node.getLeft())).setColor(NodeColor.RED);
            rightLeft = false;
        } else if (this.leftRight)  // for left and then right.
        {
            Node leftNode = rotateRight(node.getLeft());
            node.setLeft(leftNode);
            ((ColoredNode)node.getLeft()).setParent(node);
            node = rotateRight(node);
            ((ColoredNode) node).setColor(NodeColor.BLACK);
            ((ColoredNode) (node.getRight())).setColor(NodeColor.RED);
            leftRight = false;
        }


        // when rotation and recolouring is done flags are reset.
        // Now lets take care of RED RED conflict
        if (redRedConflict) {
            if (((ColoredNode) node).getParent().getRight() == node)  // to check which child is the current node of its parent
            {
                if (((ColoredNode) node).getParent().getLeft() == null || ((ColoredNode) (((ColoredNode) node).getParent().getLeft())).getColor() == NodeColor.BLACK)  // case when parent's sibling is black
                {// perform certain rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
                    if (node.getLeft() != null && ((ColoredNode) node.getLeft()).getColor() == NodeColor.RED)
                        rightLeft = true;
                    else if (node.getRight() != null && ((ColoredNode) node.getRight()).getColor() == NodeColor.RED)
                        leftLeft = true;
                } else // case when parent's sibling is red
                {
                    ((ColoredNode) ((ColoredNode) node).getParent().getLeft()).setColor(NodeColor.BLACK);
                    ((ColoredNode) node).setColor(NodeColor.BLACK);
                    if (((ColoredNode) node).getParent() != root)
                        ((ColoredNode) ((ColoredNode) node).getParent()).setColor(NodeColor.RED);
                }
            } else {
                if (((ColoredNode) node).getParent().getRight() == null || ((ColoredNode) (((ColoredNode) node).getParent().getRight())).getColor() == NodeColor.BLACK)  // case when parent's sibling is black
                {
                    if (node.getLeft() != null && ((ColoredNode) node.getLeft()).getColor() == NodeColor.RED)
                        rightRight = true;
                    else if (node.getRight() != null && ((ColoredNode) node.getRight()).getColor() == NodeColor.RED)
                        leftRight = true;
                } else {
                    ((ColoredNode) ((ColoredNode) node).getParent().getRight()).setColor(NodeColor.BLACK);
                    ((ColoredNode) node).setColor(NodeColor.BLACK);
                    if (((ColoredNode) node).getParent() != root)
                        ((ColoredNode) ((ColoredNode) node).getParent()).setColor(NodeColor.RED);
                }
            }
            redRedConflict = false;
        }

        return (ColoredNode) node;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.getRight();
        Node temp = newRoot.getLeft();
        newRoot.setLeft(node);
        node.setRight(temp);
        ((ColoredNode) node).setParent(newRoot);

        if (temp != null)
            ((ColoredNode) temp).setParent(node);

        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.getLeft();
        Node temp = newRoot.getRight();
        newRoot.setRight(node);
        node.setLeft(temp);

        ((ColoredNode) node).setParent(newRoot);

        if (temp != null)
            ((ColoredNode) temp).setParent(node);

        return newRoot;
    }


    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public void printInorder() {
        System.out.print("[");
        printInorder(root);
        System.out.println("]");
    }

    private void printInorder(Node root) {
        if (root != null) {
            printInorder(root.getLeft());
            System.out.print(root.getValue() + ", ");
            printInorder(root.getRight());
        }
    }

}
