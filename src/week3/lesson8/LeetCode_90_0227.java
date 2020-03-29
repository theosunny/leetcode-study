package week3.lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_90_0227 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums.length < 1) return res;
        Arrays.sort(nums);
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
        cur.remove(cur.size() - 1);
        while (index + 1 < nums.length && nums[index + 1] == nums[index]) {
            index++;
        }
        helper(res, cur, index + 1, nums);

    }

    public static void main(String[] args) {
        LeetCode_90_0227 t = new LeetCode_90_0227();
        System.out.println(t.subsetsWithDup(new int[]{1, 2, 2}));
    }
}
