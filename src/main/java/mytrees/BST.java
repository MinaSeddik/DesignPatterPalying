package mytrees;

public class BST {

    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        } else if (value < root.getValue()) {
            Node leftNode = insert(root.getLeft(), value);
            root.setLeft(leftNode);
        } else {
            Node rightNode = insert(root.getRight(), value);
            root.setRight(rightNode);
        }

        return root;
    }


    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if( node == null ) return null;

        if(value < node.getValue()){
            Node leftNode = delete(node.getLeft(), value);
            node.setLeft(leftNode);
        }else if(value > node.getValue()){
            Node rightNode = delete(node.getRight(), value);
            node.setRight(rightNode);
        }else{

            if(node.getLeft() == null){
                return node.getRight();
            }else if(node.getRight() == null){
                return node.getLeft();
            }

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

        return node;
    }

    public void printInorder() {
        System.out.print("[");
        printInorder(root);
        System.out.println("]");
    }

    private void printInorder(Node root) {
        if(root != null) {
            printInorder(root.getLeft());
            System.out.print(root.getValue() + ", ");
            printInorder(root.getRight());
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

}
