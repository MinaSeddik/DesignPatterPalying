package heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> {

    private List<E> list;

    public MaxHeap() {
        this.list = new ArrayList();
    }

    public void add(E element) {
        list.add(element);
        int elementIndex = list.size() - 1;

        while (hasInferiorParent(elementIndex)) {
            swapElementWithParent(elementIndex);
            elementIndex = getParentIndex(elementIndex);
        }

    }

    private boolean hasInferiorParent(int elementIndex) {
        if( elementIndex <= 0)  return false;

        E parent = getParent(elementIndex);
        E element = list.get(elementIndex);
        return parent.compareTo(element) < 0;
    }

    private void swapElementWithParent(int elementIndex) {
        int parentIndex = getParentIndex(elementIndex);
        swap(elementIndex, parentIndex);
    }

    private void swap(int index1, int index2) {
        E temp = list.get(index2);
        list.set(index2, list.get(index1));
        list.set(index1, temp);
    }

    private int getParentIndex(int elementIndex) {
        return (elementIndex - 1) / 2;
    }

    private E getParent(int elementIndex) {
        int parentIndex = getParentIndex(elementIndex);
        return list.get(parentIndex);
    }

    public E remove() throws Exception {
        if (list.isEmpty()) {
            throw new Exception(String.format("Can't remove element from an empty heap."));
        }

        E result = list.get(0);
        moveLastElementToTheTop();

        int currentElementIndex = 0;
        while (hasChildren(currentElementIndex)) {
            currentElementIndex = swapWithChildIfNeededAndReturnSwappedIndex(currentElementIndex);
        }

        return result;
    }

    private boolean hasChildren(int elementIndex) {
        if (elementIndex < 0) return false;

        int leftChildIndex = getLeftChildIndex(elementIndex);
        int rightChildIndex = getRightChildIndex(elementIndex);

        return leftChildIndex < size() && rightChildIndex < size();
    }

    private int getLeftChildIndex(int elementIndex) {
        return elementIndex * 2 + 1;
    }

    private int getRightChildIndex(int elementIndex) {
        return elementIndex * 2 + 2;
    }

    private E getLeftChild(int elementIndex) {
        int leftChildIndex = getLeftChildIndex(elementIndex);
        return list.get(leftChildIndex);
    }

    private E getRightChild(int elementIndex) {
        int rightChildIndex = getRightChildIndex(elementIndex);
        return list.get(rightChildIndex);
    }

    private void moveLastElementToTheTop() {
        int lastElementIndex = list.size() - 1;

        E lastElement = list.get(lastElementIndex);
        list.set(0, lastElement);
        list.remove(lastElementIndex);
    }

    private int swapWithChildIfNeededAndReturnSwappedIndex(int parentIndex) {
        if (!hasChildren(parentIndex)) return -1;

        int swappedIndex = -1;
        if (hasLeftChildOnly(parentIndex)) {
            if (hasSuperiorLeftChild(parentIndex)) {
                swapParentWithLeftChild(parentIndex);
                swappedIndex = getLeftChildIndex(parentIndex);
            }
        } else if (leftChildGreaterThanOrEqualRightChild(parentIndex) && hasSuperiorLeftChild(parentIndex)) {
            swapParentWithLeftChild(parentIndex);
            swappedIndex = getLeftChildIndex(parentIndex);
        } else if (rightChildGreaterThanLeftChild(parentIndex) && hasSuperiorRightChild(parentIndex)) {
            swapParentWithRightChild(parentIndex);
            swappedIndex = getRightChildIndex(parentIndex);
        }

        return swappedIndex;
    }

    private boolean leftChildGreaterThanOrEqualRightChild(int parentIndex) {
        E leftChild = getLeftChild(parentIndex);
        E rightChild = getRightChild(parentIndex);
        return leftChild.compareTo(rightChild) > 0 || leftChild.compareTo(rightChild) == 0;
    }

    private boolean rightChildGreaterThanLeftChild(int parentIndex) {
        E leftChild = getLeftChild(parentIndex);
        E rightChild = getRightChild(parentIndex);
        return rightChild.compareTo(leftChild) > 0;
    }

    private boolean hasSuperiorLeftChild(int parentIndex) {
        E parent = list.get(parentIndex);
        E leftChild = getLeftChild(parentIndex);

        return parent.compareTo(leftChild) < 0;
    }

    private boolean hasSuperiorRightChild(int parentIndex) {
        E parent = list.get(parentIndex);
        E rightChild = getRightChild(parentIndex);

        return parent.compareTo(rightChild) < 0;
    }

    private boolean hasLeftChildOnly(int elementIndex) {
        return getLeftChildIndex(elementIndex) < size() && getRightChildIndex(elementIndex) >= size();
    }

    private void swapParentWithLeftChild(int parentIndex) {
        int leftChildIndex = getLeftChildIndex(parentIndex);
        swap(parentIndex, leftChildIndex);
    }

    private void swapParentWithRightChild(int parentIndex) {
        int rightChildIndex = getRightChildIndex(parentIndex);
        swap(parentIndex, rightChildIndex);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        list.stream().forEach(e -> stringBuilder.append(e + ", "));
        if (list.size() > 1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
