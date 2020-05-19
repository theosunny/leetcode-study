package so;

import java.util.Random;

public class QuickSort {
    /**
     *
     */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start == end) return;
        int index = partition(nums, nums.length, start, end);
        if (index > start) quickSort(nums, start, index - 1);
        if (index < end) quickSort(nums, index + 1, end);
    }

    private int partition(int[] nums, int length, int start, int end) {
        if (nums == null || length <= 0 || start < 0 || end >= length) {
            throw new IllegalArgumentException("params error");
        }
        int index = getRandom(start, end);
        swap(nums, end, index);
        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[index] < nums[end]) {
                small++;
                if (small != index) {
                    swap(nums, index, small);
                }
            }
        }
        ++small;
        swap(nums, small, end);
        return small;
    }

    private void swap(int[] nums, int end, int index) {
        int tmp = nums[index];
        nums[end] = nums[index];
        nums[index] = tmp;
    }

    public int getRandom(int min, int max) {
        Random random = new Random();
        int i = random.nextInt(max - min) + min;
        return i;
    }

    public static void main(String[] args) {

        int[] data = {1, 0, 4, 12, 4, 3, 9};
        QuickSort quickSort = new QuickSort();
        int random = quickSort.getRandom(1, 3);
        System.out.println(random);
        quickSort.quickSort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print(",");
        }
    }
}
