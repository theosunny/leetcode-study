package week4.lesson10;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class leetcode_55 {
    public boolean canJump(int[] nums) {
//        return method1(nums);
//        return method2(nums);
//        return method3(nums);
        return method4(nums);
    }

    /**
     * 自底向上的动态规划
     *
     * @param nums
     * @return
     */
    private boolean method4(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }

            }
        }
        return memo[0] == Index.GOOD;
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    /**
     * 其实就是回溯的优化，自顶向下的动态规划
     *
     * @param nums
     * @return
     */

    private boolean method3(int[] nums) {
        if (nums == null) return false;
        momo = new Index[nums.length];
        for (int i = 0; i < momo.length; i++) {
            momo[i] = Index.UNKNOWN;
        }
        momo[momo.length - 1] = Index.GOOD;
        return helperMethod3(nums, 0);
    }

    Index[] momo;

    public boolean helperMethod3(int[] nums, int position) {
        if (momo[position] != Index.UNKNOWN) {
            return momo[position] == Index.GOOD ? true : false;
        }
        int furtherStep = Math.min(nums[position] + position, nums.length - 1);
        for (int i = position + 1; i <= furtherStep; i++) {
            if (helperMethod3(nums, i)) {
                momo[position] = Index.GOOD;
                return true;
            }
        }
        momo[position] = Index.BAD;
        return false;
    }

    //递归，超时间了
    private boolean method2(int[] nums) {
        if (nums == null) return false;
        return helper(nums, 0);
    }

    private boolean helper(int[] nums, int position) {
        //terminator
        if (position == nums.length - 1) return true;
        //process
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int j = position + 1; j <= furthestJump; j++) {
            if (helper(nums, +j)) {
                return true;
            }

        }
        //drill down
        //revert status
        return false;
    }

    /**
     * 贪心算法
     *
     * @param nums
     * @return
     */
    private boolean method1(int[] nums) {
        if (nums == null) return false;
        int res = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= res) {
                res = i;
            }
        }
        return res == 0;
    }

    public static void main(String[] args) {
//        boolean t = new leetcode_55().canJump(new int[]{3, 2, 1, 0, 4});
        boolean t = new leetcode_55().canJump(new int[]{2, 0, 0});
        System.out.println(t);
    }
}
