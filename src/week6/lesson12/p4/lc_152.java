package week6.lesson12.p4;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class lc_152 {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        //1 状态的定义 dp_max[i] = 前i个元素的最大的连续子数组乘积
        int[] dp_max = new int[nums.length + 1];
        int[] dp_min = new int[nums.length + 1];
        int max = Integer.MIN_VALUE;
        // 由于存在负数，所以需要维护两个数组
        // dp_max[i] 指的是以第 i 个数结尾的 乘积最大 的连续子序列
        // dp_min[i] 指的是以第 i 个数结尾的 乘积最小 的连续子序列

        //2、 初始化状态数组，数组中的一个元素的积就是第一个元素的值
        dp_max[0] = 1;
        dp_min[0] = 1;
        //3、dp_max 方程 dp_max[i] = max(dp_max[i-1],0) + dp_max[i]
        for (int i = 1; i < nums.length; i++) {
            // 如果数组的数是负数，那么会导致 max 变成 min，min 变成 max
            // 故需要交换dp
            if (nums[i - 1] < 0) {
                int temp = dp_min[i - 1];
                dp_min[i - 1] = dp_max[i - 1];
                dp_max[i - 1] = temp;
            }
            dp_min[i] = Math.min(nums[i - 1], dp_min[i - 1] * nums[i - 1]);
            dp_max[i] = Math.max(nums[i - 1], dp_max[i - 1] * nums[i - 1]);
            max = Math.max(max, dp_max[i]);
        }
        return max;
    }

    public int maxProductsimplify(int[] nums) {
        if (nums.length == 0) return 0;
        //1 状态的定义 dp_max[i] = 前i个元素的最大的连续子数组乘积
        int dp_max = 1;
        int dp_min = 1;
        int max = Integer.MIN_VALUE;
        // 由于存在负数，所以需要维护两个数组
        // dp_max[i] 指的是以第 i 个数结尾的 乘积最大 的连续子序列
        // dp_min[i] 指的是以第 i 个数结尾的 乘积最小 的连续子序列

        //2、 初始化状态数组，数组中的一个元素的积就是第一个元素的值

        //3、dp_max 方程 dp_max[i] = max(dp_max[i-1],0) + dp_max[i]
        for (int i = 0; i < nums.length; i++) {
            // 如果数组的数是负数，那么会导致 max 变成 min，min 变成 max
            // 故需要交换dp
            if (nums[i] < 0) {
                int temp = nums[i - 1];
                dp_min = dp_max;
                dp_max = temp;
            }
            dp_min = Math.min(nums[i], dp_min * nums[i]);
            dp_max = Math.max(nums[i], dp_max * nums[i]);
            max = Math.max(max, dp_max);
        }
        return max;
    }

    //分治
    public int maxProductFz(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int p = (left + right) / 2;
        int leftMultiply = helper(nums, left, p);
        int rightMultiply = helper(nums, p + 1, right);
        int crossMultiply = crossMultiply(nums, left, p, right);

        return Math.max(Math.max(leftMultiply, rightMultiply), crossMultiply);
    }

    private int crossMultiply(int[] nums, int left, int p, int right) {
        if (left == right) return nums[left];
        int curMultiply = 0;
        int leftMultiply = Integer.MIN_VALUE;
        for (int i = p; i >= left; i--) {
            curMultiply *= nums[i];
            leftMultiply = Math.max(leftMultiply, curMultiply);
        }
        curMultiply = 0;
        int rightMultiply = Integer.MIN_VALUE;
        for (int i = p + 1; i <= right; i++) {
            curMultiply *= nums[i];
            rightMultiply = Math.max(rightMultiply, curMultiply);
        }
        return rightMultiply * leftMultiply;
    }
}
