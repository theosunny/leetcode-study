package five.week1_1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
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
public class lc_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) return null;
        int n = nums.length;
        if (n * k == 0) {
            return nums;
        }
        int[] list = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            list[i] = max;
        }
        return list;
    }

    ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
    int[] nums;


    /**
     * 使用队列。
     * 先初始化前k个数据，填充队列。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowQueue(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        this.nums = nums;

        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            clearDequ(i, k);
            deque.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[maxIndex];
        for (int i = k; i < n; i++) {
            clearDequ(i, k);
            deque.addLast(i);
            output[i - k + 1] = nums[deque.getFirst()];
        }

        return output;
    }

    private void clearDequ(int i, int k) {
        //i超过当前队列了，要出队
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }
        //如果新的索引元素，dayu当前队列中的元素，则最后元素出队
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
    }




    public static void main(String[] args) {
        int[] arr = new lc_239().maxSlidingWindowQueue(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
