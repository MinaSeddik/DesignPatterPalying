package tree;


// Tree class
class TAVLTree {
    TNode root;

    int height(TNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    TNode rightRotate(TNode y) {
        TNode x = y.left;
        TNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    TNode leftRotate(TNode x) {
        TNode y = x.right;
        TNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor of a node
    int getBalanceFactor(TNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a node
    TNode insertNode(TNode TNode, int item) {

        // Find the position and insert the node
        if (TNode == null)
            return (new TNode(item));
        if (item < TNode.item)
            TNode.left = insertNode(TNode.left, item);
        else if (item > TNode.item)
            TNode.right = insertNode(TNode.right, item);
        else
            return TNode;

        // Update the balance factor of each node
        // And, balance the tree
        TNode.height = 1 + max(height(TNode.left), height(TNode.right));
        int balanceFactor = getBalanceFactor(TNode);
        if (balanceFactor > 1) {
            if (item < TNode.left.item) {
                return rightRotate(TNode);
            } else if (item > TNode.left.item) {
                TNode.left = leftRotate(TNode.left);
                return rightRotate(TNode);
            }
        }
        if (balanceFactor < -1) {
            if (item > TNode.right.item) {
                return leftRotate(TNode);
            } else if (item < TNode.right.item) {
                TNode.right = rightRotate(TNode.right);
                return leftRotate(TNode);
            }
        }
        return TNode;
    }

    TNode nodeWithMimumValue(TNode TNode) {
        TNode current = TNode;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node
    TNode deleteNode(TNode root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (item < root.item)
            root.left = deleteNode(root.left, item);
        else if (item > root.item)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                TNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                TNode temp = nodeWithMimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item);
            }
        }
        if (root == null)
            return root;

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    void preOrder(TNode TNode) {
        if (TNode != null) {
            System.out.print(TNode.item + " ");
            preOrder(TNode.left);
            preOrder(TNode.right);
        }
    }

    // Print the tree
    public void printTree(TNode currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.item);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
}
