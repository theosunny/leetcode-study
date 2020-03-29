package week3.lesson6.heap;

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
public class LeetCode_40_0227 {
    public int[] getLeastNumbers(int[] arr, int k) {
        return method1(arr, k);
    }

    /**
     * 直接排序
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
     * 快速排序思想
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] method2(int[] arr, int k) {
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

    /**
     * 最小堆实现
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] method3(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        int[] rs = new int[k];
        for (int i = 0; i < k; i++) {
            rs[i] = priorityQueue.poll();
        }
        return rs;
    }

    /**
     * 最大堆实现
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] method4(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }
        int[] rs = new int[k];
        int i = 0;
        for (int num : pq) {
            rs[i++] = num;
        }
        return rs;
    }

    /**
     * 二叉搜索属
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] method5(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //代表map存了多少数字
        int cnt = 0;

        for (int num : arr) {
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;

            }
            // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
            //    若当前数字比map中最大的数字还大，就直接忽略；
            //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            if (entry.getKey() > num) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (entry.getValue() == 1) {
                    map.pollLastEntry();
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }
        // 最后返回map中的元素
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            while (freq-- > 0) {
                res[idx++] = entry.getKey();
            }
        }
        return res;

    }

}
