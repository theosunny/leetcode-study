package clockIn;

import java.util.*;

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
public class Lc_40_getLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        else if (arr.length < k) return arr;
        // 最后一个参数表示我们要找的是下标为k-1的数
        partitionArray(arr, 0, arr.length - 1, k);
        // 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void partitionArray(int[] arr, int lo, int hi, int k) {
        int m = partition(arr, lo, hi);
        //正好找到k个数
        if (k==m){
            return ;
        }
        else if (k<m){
            partitionArray(arr,lo,m-1,k);
        }else {
            partitionArray(arr, m+1, hi, k);
        }
    }

    private int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = arr[lo];
        while (true) {
            while (arr[++i] < v) {
                if (i == hi) break;
            }
            while (arr[--j] > v) {
                if (j == lo) break;
            }
            if (i > j) break;
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

    /**
     * 暴力
     *
     * @param arr
     * @param k
     * @return
     */
    private int[] method1(int[] arr, int k) {
        Arrays.sort(arr);
        int[] rs = new int[k];
        System.arraycopy(arr, 0, rs, 0, k);
        return rs;
    }

    /**
     * 最小堆
     * 判断堆顶元素（最大值）是否是小于入堆的元素，如果不是则弹出，是则下一个入堆
     *
     * @param arr
     * @param k
     * @return
     */

    private int[] method2(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if (t1 > t2) {
                    return -1;
                } else if (t1 < t2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (!queue.isEmpty() && queue.peek() > arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int[] rs = new int[k];
        Iterator<Integer> iterator = queue.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            rs[i++] = iterator.next();
        }
        return rs;
    }


    public static void main(String[] args) {
        int[] ints = new Lc_40_getLeastNumbers().method2(new int[]{1, 2, 3}, 2);
        System.out.println(1);
    }

}
