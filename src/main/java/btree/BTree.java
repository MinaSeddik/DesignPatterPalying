package btree;

public class BTree {

    private Node root;
    private int N;

    public BTree(int n) {
        this.N = n;
        root = new Node(N);
    }


    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null) return null;

        int childIndex;
        for (childIndex = 0; childIndex < node.getChildrenCount(); childIndex++) {
            if (node.getNthKey(childIndex) == value) {
                return node;
            }
            if (node.getNthKey(childIndex) > value) {
                break;
            }
        }

        return node.isLeaf() ? null : search(node.getNthChild(childIndex), value);
    }

    public void insert(int value) {

        if (root.getNumberOfEntries() == N - 1) {
            Node newRoot = new Node(N);
            newRoot.setLeaf(false);

            Node firstChild = root;
            newRoot.setNthChild(0, firstChild);
            root = newRoot;

            split(newRoot, 0, firstChild);
            insert(newRoot, value);
        } else {
            insert(root, value);
        }
    }


    private void insert(Node node, int value) {

        if (node.isLeaf()) {
            int i;
            for (i = node.getNumberOfEntries() - 1; i >= 0; i--) {
                if (node.getNthKey(i) < value) break;
                node.setNthKey(i + 1, node.getNthKey(i));
            }
            node.setNthKey(i + 1, value);
            node.setNumberOfEntries(node.getNumberOfEntries() + 1);
        } else {
            int i;
            for (i = node.getNumberOfEntries() - 1; i >= 0; i--) {
                if (node.getNthKey(i) < value) break;
            }
            Node temp = node.getNthChild(++i);
            if (temp.getNumberOfEntries() == N - 1) {
                split(node, i, temp);
                if (value > node.getNthKey(i)) i++;
            }
            insert(node.getNthChild(i), value);
        }

    }


    private void split(Node parent, int pos, Node node) {

        Node node2 = new Node(N);
        node2.setLeaf(node.isLeaf());

        // (1) split the data
        for (int i = 0; i < (isEvenTree() ? N / 2 - 1 : N / 2); ++i) {
            node2.setNthKey(i, node.getNthKey(i + N / 2));
        }
        node.setNumberOfEntries(N / 2 - 1);
        node2.setNumberOfEntries(isEvenTree() ? N / 2 - 1 : N / 2);

        // (2) move the pointers
        if (!node.isLeaf()) {
            for (int i = 0; i < (isEvenTree() ? N / 2 : N / 2 + 1); ++i) {
                node2.setNthChild(i, node.getNthChild(i + N / 2));
            }
        }

        for (int i = parent.getNumberOfEntries(); i >= pos + 1; i--) {
            parent.setNthChild(i + 1, parent.getNthChild(i));
        }
        parent.setNthChild(pos + 1, node2);


        for (int i = parent.getNumberOfEntries() - 1; i >= pos; i--) {
            parent.setNthKey(i + 1, parent.getNthKey(i));
        }
        parent.setNthKey(pos, node.getNthKey(N / 2 - 1));
        parent.setNumberOfEntries(parent.getNumberOfEntries() + 1);
    }


    private boolean isEvenTree() {
        return N % 2 == 0;
    }

    public void show() {
        show(root);
    }

    // Display
    private void show(Node node) {
        assert (node == null);
        for (int i = 0; i < node.getNumberOfEntries(); i++) {
            System.out.print(node.getNthKey(i) + " ");
        }
        if (!node.isLeaf()) {
            for (int i = 0; i < node.getNumberOfEntries() + 1; i++) {
                show(node.getNthChild(i));
            }
        }
    }


}
