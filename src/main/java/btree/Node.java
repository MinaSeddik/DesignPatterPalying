package btree;

public class Node {

    private int[] key;
    private Node[] child;
    private boolean isLeaf;
    private int n;

    public Node(int children) {
        if (children < 2) {
            throw new IllegalArgumentException(String.format("Invalid children= %d, B-tree Node must have at least 2 children", children));
        }

        int numOfNode = children;
        int numOfKeys = children - 1;

        child = new Node[numOfNode];
        key = new int[numOfKeys];
        n = 0;
        isLeaf = true;
    }

    public int getChildrenCount() {
        return child.length;
    }

    public Node getNthChild(int index) {
        return child[index];
    }


    public int getNthKey(int index) {
        return key[index];
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getNumberOfEntries() {
        return n;
    }

    public void setNumberOfEntries(int entries) {
        n = entries;
    }

    public void setNthKey(int index, int value) {
        key[index] = value;
    }

    public void setNthChild(int index, Node node) {
        child[index] = node;
    }
}
