package five.week1_1;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class lc_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        return method1(nums);
    }

    //暴力法，
    private List<List<Integer>> method1(int[] nums) {
        Set<List<Integer>> rs = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        rs.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(rs);
    }

    //暴力法，+ hashmap
    private List<List<Integer>> method2(int[] nums) {
        Set<List<Integer>> rs = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                int target = -nums[i] - nums[j];
                if (map.containsKey(target) && map.get(target) != j) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(target);
                    Collections.sort(list);
                    rs.add(list);
                }
                map.put(nums[j], i);
            }
        }
        return new ArrayList<>(rs);
    }

    //双指针
    private List<List<Integer>> method3(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int t = nums[l] + nums[r] + nums[i];
                if (t > 0) {
                    while (l < r && nums[r] == nums[--r]) ;
                } else if (t < 0) {
                    while (l < r && nums[l] == nums[++l]) ;
                } else {
                    rs.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    while (l < r && nums[l] == nums[++l]) ;
                    while (l < r && nums[r] == nums[--r]) ;
                }
            }
        }
        return rs;
    }
}
