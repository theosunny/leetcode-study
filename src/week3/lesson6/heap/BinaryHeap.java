package week3.lesson6.heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryHeap {
    /**
     *
     */
    private static final int d = 2;

    private int[] heap;

    private int heapSize;

    public BinaryHeap(int capacity) {
        this.heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public int parent(int i) {
        return (i - 1) / d;
    }

    private int kthChild(int i, int k) {
        return d * i + k;
    }

    public void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("heap is full , no space to save");
        }
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    private void heapifyUp(int i) {
        int insertVal = heap[i];
        while (i > 0 && insertVal > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = insertVal;
    }

    public int delete(int x) {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty , no data to delete");
        }
        int maxElement = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return maxElement;
    }

    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp >= heap[child]) {
                break;
            }
            heap[i] = heap[child];
            i = child;
        }
        heap[i] = temp;

    }

    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;

    }

    public void printHeap() {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public int findMax() {
        if (isEmpty()) throw new NoSuchElementException("heap is empty");
        return heap[0];
    }

    public static void main(String[] args) {
        BinaryHeap maxHeap = new BinaryHeap(10);
        maxHeap.insert(10);
        maxHeap.insert(4);
        System.out.println("max element is " + maxHeap.findMax());
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(5);
        maxHeap.insert(3);


        maxHeap.printHeap();
        maxHeap.delete(5);
        maxHeap.printHeap();
        maxHeap.delete(2);
        maxHeap.printHeap();
    }
}
