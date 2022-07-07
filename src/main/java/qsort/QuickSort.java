package qsort;

import java.util.List;

public class QuickSort<E extends Comparable<E>> {

    public void sort(List<E> list) {
        qsort(list, 0, list.size() - 1);
    }

    private void qsort(List<E> list, int low, int high) {
        if (low <= high) {
            int pivotIndex = partition(list, low, high);

            qsort(list, low, pivotIndex - 1);
            qsort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<E> list, int low, int high) {

        int i = low, j = high;

        E pivot = list.get(low);
        while (i < j) {
            while (i < high && list.get(i).compareTo(pivot) <= 0) i++;
            while (j >= low && list.get(j).compareTo(pivot) > 0) j--;

            if(j <=  i) break;

            if (i < j) swapElements(list, i, j);
        }

        swapElements(list, low, j);
        return j;
    }

    private void swapElements(List<E> list, int i, int j) {

        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);

    }


}
