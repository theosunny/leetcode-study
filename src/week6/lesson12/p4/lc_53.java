package week6.lesson12.p4;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class lc_53 {
    //贪心算法 其实是动态规划的优化
    public int maxSubArray(int[] nums) {
        //从第一个元素开始，一个个迭代
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        // 使用 currSum 代替 dp[i]
        int currSum = nums[0];
        ans = nums[0];

        for (int i = 0; i < nums.length; i++) {
            currSum = Math.max(currSum, 0) + nums[i];
            // 更新最大和
            ans = Math.max(ans, currSum);
        }
        return ans;
    }

    //更加简洁的贪心
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    //dp
    public int maxSubArrayDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], nums[i - 1] + maxSum);
        }
        return maxSum;
    }

    public int maxSubArraystandardDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        //1、状态的定义
        //dp[i]表示前i个元素的最大连续子数组的和
        int[] dp = new int[nums.length];
        //2、初始化状态数组，数组中的一个元素的和就是第一个元素的值
        dp[0] = nums[0];
        ans = nums[0];
        //3 状态转移
        //转移方程 dp[i] = max(dp[i-1],0)+nums[i]
        //  dp 当前元素的值等于前一个元素值和 0 的最大值再加上 nums[i]
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            //更新最大和
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //分治解法 其实可以拆分为
    // mid = (right + left) /2
    // left = (left ,mid)
    // right = (mid + 1, right)
    //以及 left + right 组成的结果集一共三个
    public int maxSubArraysfz(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int joinsum = crossSum(nums, left, mid, right);
        return Math.max(Math.max(leftSum, rightSum), joinsum);
    }

    private int crossSum(int[] nums, int left, int mid, int right) {
        if (left == right) return nums[left];
        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = mid; i >= left ; i--) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }
        currSum = 0;
        int rightsubSum = Integer.MIN_VALUE;

        for (int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            rightsubSum = Math.max(rightsubSum, currSum);
        }
        return rightsubSum + leftSubsum;
    }
}
