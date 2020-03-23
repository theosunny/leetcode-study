package clockIn;

import java.util.Arrays;

/**
 * 面试题40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class Lc_40_getLeastNumbers_EX {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        return partitionArr(arr, 0, arr.length - 1, k - 1);
    }

    private int[] partitionArr(int[] arr, int lo, int hi, int k) {
        int m = partition(arr, lo, hi);
        if (m == k) return Arrays.copyOf(arr, m + 1);
        return m > k ? partitionArr(arr, lo, m - 1, k) : partitionArr(arr, m + 1, hi, k);
    }

    private int partition(int[] arr, int lo, int hi) {
        int v = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && arr[i] < v) ;
            while (--j >= lo && arr[j] > v) ;
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }



    public static void main(String[] args) {
         int[] ints = new Lc_40_getLeastNumbers_EX().getLeastNumbers(new int[]{0,1,2,1}, 1);
        System.out.println(1);
    }

}
