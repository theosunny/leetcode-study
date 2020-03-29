package week3.lesson8;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class LeetCode_78_0227 {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length < 1) return res;
        helper(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> cur, int index, int[] nums) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[index]);
        helper(res, cur, index + 1, nums);
        //又可能不选择他
        cur.remove(cur.size() - 1);
        helper(res, cur, index + 1, nums);
    }
}
