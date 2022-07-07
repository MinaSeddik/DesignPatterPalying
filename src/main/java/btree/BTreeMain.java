package btree;

public class BTreeMain {


    public static void main(String[] args) {
        int N = 5;

        BTree bTree = new BTree(N);

        bTree.insert(40);
        bTree.insert(20);
        bTree.insert(10);
        bTree.insert(30);
        bTree.insert(50);
        bTree.insert(5);
        bTree.insert(0);
        bTree.insert(150);
        bTree.insert(350);
        bTree.insert(550);
        bTree.insert(510);
        bTree.insert(15);
        bTree.insert(550);
        bTree.insert(630);
        bTree.insert(300);


        bTree.show();

    }




}
