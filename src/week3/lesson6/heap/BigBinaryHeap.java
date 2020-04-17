package week3.lesson6.heap;

import java.util.NoSuchElementException;

/**
 * 大顶堆
 */
public class BigBinaryHeap {
    private static final int d = 2;
    private int[] heap;
    private int heapSize;

    public BigBinaryHeap(int capacity) {
        this.heapSize = 0;
        this.heap = new int[capacity + 1];
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int parent(int i) {
        return (i - 1) / d;
    }

    public int kthChild(int i, int k) {
        return i * d + k;
    }

    public void insert(int v) {
        if (isFull()) throw new NoSuchElementException("the heap is full.no sapce to insert");
        heap[heapSize++] = v;
        heapifyUp(heapSize - 1);
    }

    private void heapifyUp(int i) {
        int insertValue = heap[i];
        while (i > 0 && insertValue > heap[parent(i)]) {
            //只要当前的值大于其父亲的值，那么就将父亲的值替换成当前的值，进行交换
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = insertValue;
    }

    /**
     * @param x 待删除的索引
     * @return
     */
    private int delete(int x) {
        if (isEmpty()) throw new NoSuchElementException("the heap is empty , no element to delete");
        int maxElement = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return maxElement;
    }

    private void heapifyDown(int i) {
        int temp = heap[i];
        int child;
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp >= heap[child]) break;
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
        BigBinaryHeap maxHeap = new BigBinaryHeap(10);
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
