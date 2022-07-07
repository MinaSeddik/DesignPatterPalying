package mytrees;

public class MainBST {


    public static void main(String[] args) {

        BST tree = new BST();
        tree.insert(33);
        tree.insert(13);
        tree.insert(53);
        tree.insert(9);
        tree.insert(21);
        tree.insert(61);
        tree.insert(8);
        tree.insert(11);

        tree.printInorder();


        int val = 13;
        tree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        tree.printInorder();


        val = 5;
        tree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        tree.printInorder();


        val = 9;
        tree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        tree.printInorder();


        val = 33;
        tree.delete(val);
        System.out.println();
        System.out.println("After Deletion: " + val);
        tree.printInorder();


    }
}
