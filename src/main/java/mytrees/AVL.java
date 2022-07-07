package mytrees;

public class AVL {

    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {

        if (node == null) {
            return new Node(value);
        } else if (value < node.getValue()) {
            Node leftNode = insert(node.getLeft(), value);
            node.setLeft(leftNode);
        } else {
            Node rightNode = insert(node.getRight(), value);
            node.setRight(rightNode);
        }

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (value < node.getLeft().getValue()) {
                node = rotateRight(node);
            } else if (value > node.getLeft().getValue()) {
                Node leftNode = rotateLeft(node.getLeft());
                node.setLeft(leftNode);
            }
        } else if (balanceFactor < -1) {
            if (value > node.getRight().getValue()) {
                node = rotateLeft(node);
            } else if (value < node.getRight().getValue()) {
                Node rightNode = rotateRight(node.getRight());
                node.setLeft(rightNode);
            }
        }

        return node;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) return null;

        if (value < node.getValue()) {
            Node leftNode = delete(node.getLeft(), value);
            node.setLeft(leftNode);
        } else if (value > node.getValue()) {
            Node rightNode = insert(node.getRight(), value);
            node.setRight(rightNode);
        } else {
            if (node.getRight() == null && node.getLeft() == null) {
                node = null;
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else {
                Node tempNode = node.getRight();
                int minValue = tempNode.getValue();
                while(tempNode.getLeft() != null){
                    minValue = tempNode.getValue();
                    tempNode = tempNode.getLeft();
                }
                node.setValue(minValue);

                tempNode = delete(node.getRight(), minValue);
                node.setRight(tempNode);
            }
        }

        if ( node == null) return null;

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (value < node.getLeft().getValue()) {
                node = rotateRight(node);
            } else if (value > node.getLeft().getValue()) {
                Node leftNode = rotateLeft(node.getLeft());
                node.setLeft(leftNode);
            }
        } else if (balanceFactor < -1) {
            if (value > node.getRight().getValue()) {
                node = rotateLeft(node);
            } else if (value < node.getRight().getValue()) {
                Node rightNode = rotateRight(node.getRight());
                node.setLeft(rightNode);
            }
        }

        return node;
    }


    private Node rotateLeft(Node node) {
        Node newRoot = node.getRight();
        Node temp = newRoot.getLeft();
        newRoot.setLeft(node);
        node.setRight(temp);

        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.getLeft();
        Node temp = newRoot.getRight();
        newRoot.setRight(node);
        node.setLeft(temp);

        return newRoot;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) return 0;

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        return leftHeight - rightHeight;
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
