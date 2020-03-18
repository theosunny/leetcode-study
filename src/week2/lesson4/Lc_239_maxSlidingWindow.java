package week2.lesson4;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 */
public class Lc_239_maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return method1(nums, k);
    }

    /**
     * 暴力解法，多层循环
     * 1、外层循环 nums.length-k+1；
     * 2循环k，获取最大值
     *
     * @param nums
     * @param k
     * @return
     */
    private int[] method1(int[] nums, int k) {
        int n = nums.length;
        //精妙相当于判断k和length都为0
        if (n * k == 0) return new int[0];
        //计算得结果集的长度等于length - k +1;
        int[] output = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            //遍历每一个k集合，取最大值
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }

    public int[] method1Copy(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < k; j++) {
                max = Math.max(nums[i], max);
            }
            output[i] = max;
        }
        return output;
    }

    public int[] method2Copy(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            clearDequeCopy(nums, i, k);
            deque.addLast(i);
            maxIndex = nums[i] > nums[maxIndex] ? i : maxIndex;
        }

        int[] output = new int[n - k + 1];
        output[0] = nums[maxIndex];
        for (int i = k; i < nums.length; i++) {
            clearDeque(nums, i, k);
            deque.addLast(i);
            output[i - k + 1] = nums[deque.getFirst()];
        }
        return output;
    }

    private void clearDequeCopy(int[] nums, int i, int k) {
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
    }

    /**
     * 使用双端队列
     * １、首先初始化队列，
     * 对队列操作：a，先移除滑动过的失效索引，b，拿当前值和队列尾元素比较，弹出取出最小值的元素，否则直接新增
     *
     * @param nums
     * @param k
     * @return
     */
    ArrayDeque<Integer> deque = new ArrayDeque<>();

    private int[] method2(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        //精妙相当于判断k和length都为0
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        int max_index = 0;
        for (int i = 0; i < k; i++) {
            clearDeque(nums, i, k);
            deque.addLast(i);
            max_index = nums[i] > nums[max_index] ? i : max_index;
        }

        //初始化数组
        int[] output = new int[nums.length - k + 1];
        output[0] = nums[max_index];
        for (int i = k; i < n; i++) {
            clearDeque(nums, i, k);
            deque.addLast(i);
            output[i - k + 1] = nums[deque.getFirst()];
        }
        return output;
    }

    private void clearDeque(int[] nums, int i, int k) {
        if (!deque.isEmpty() && deque.getFirst() == i - k + 1) deque.removeFirst();
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
    }

    public static void main(String[] args) {
        //* 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        // * 输出: [3,3,5,5,6,7]
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr = new Lc_239_maxSlidingWindow().method2(nums, 3);
        for (int a = 0; a < arr.length; a++) {
            System.out.println(arr[a]);
        }
    }
}
