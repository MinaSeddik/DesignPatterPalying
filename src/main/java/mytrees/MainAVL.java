package mytrees;

public class MainAVL {


    public static void main(String[] args) {

//        AVL tree = new AVL();
//        tree.insert(10);
//        tree.insert(20);
//        tree.insert(30);
//        tree.insert(40);
//        tree.insert(50);
//        tree.insert(60);
//        tree.insert(70);
//        tree.insert(80);
//        tree.insert(90);
//        tree.insert(100);
//        tree.insert(110);
//        tree.insert(120);
//        tree.insert(130);
//        tree.printInorder();
//        System.out.println("Height: " + tree.height());

//        BST tree2 = new BST();
//        tree2.insert(10);
//        tree2.insert(20);
//        tree2.insert(30);
//        tree2.insert(40);
//        tree2.insert(50);
//        tree2.insert(60);
//        tree2.insert(70);
//        tree2.insert(80);
//        tree2.insert(90);
//        tree2.insert(100);
//        tree2.insert(110);
//        tree2.insert(120);
//        tree2.insert(130);
//        tree2.printInorder();
//        System.out.println("Height: " + tree2.height());


        BST bstTree = new BST();
        bstTree.insert(33);
        bstTree.insert(13);
        bstTree.insert(53);
        bstTree.insert(9);
        bstTree.insert(21);
        bstTree.insert(61);
        bstTree.insert(8);
        bstTree.insert(11);
        bstTree.printInorder();
        System.out.println("Height: " + bstTree.height());


        AVL avlTree = new AVL();
        avlTree.insert(33);
        avlTree.insert(13);
        avlTree.insert(53);
        avlTree.insert(9);
        avlTree.insert(21);
        avlTree.insert(61);
        avlTree.insert(8);
        avlTree.insert(11);
        avlTree.printInorder();
        System.out.println("Height: " + avlTree.height());

        int val = 13;
        avlTree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        avlTree.printInorder();


        val = 5;
        avlTree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        avlTree.printInorder();


        val = 9;
        avlTree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        avlTree.printInorder();


        val = 33;
        avlTree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        avlTree.printInorder();


    }
}
