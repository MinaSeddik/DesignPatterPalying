package mytrees;

public class MainRedBlackTree {


    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(33);
        redBlackTree.insert(13);
        redBlackTree.insert(53);
        redBlackTree.insert(9);
        redBlackTree.insert(21);
        redBlackTree.insert(61);
        redBlackTree.insert(8);
        redBlackTree.insert(11);
        redBlackTree.printInorder();
        System.out.println("Height: " + redBlackTree.height());



    }
}
