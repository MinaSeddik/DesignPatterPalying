package heap;

public class HeapMain {


    public static void main(String[] args) throws Exception {

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);


        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(300);
        maxHeap.add(3);
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(10);
        maxHeap.add(200);
        maxHeap.add(300);
        maxHeap.add(300);
        maxHeap.add(100);
        maxHeap.add(150);
        maxHeap.add(300);
        maxHeap.add(23);
        maxHeap.add(0);
        maxHeap.add(300);


//        int size = maxHeap.size();
        System.out.println(maxHeap);
        System.out.println();

        while(!maxHeap.isEmpty()){
//            System.out.println("Before Remove: " + maxHeap);
            System.out.println("Remove: " + maxHeap.remove());
//            System.out.println("After Remove: " + maxHeap);
//            System.out.println();
        }
    }


}
