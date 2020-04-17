package week5.exam;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class FourSums {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //方法1 hash表
//        return method1(nums, target);
        //
        return method2(nums, target);
    }

    /**
     * 方法2 使用类似二分查找，先排序
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> method2(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();
        Arrays.sort(nums);
        HashSet<List<Integer>> rs = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            //提前减枝
            //if (nums[i] > target) break;
            for (int j = i + 1; j < len - 2; j++) {
                int k = j + 1;
                int m = len - 1;
                while (k < m) {
                    int v = nums[i] + nums[j] + nums[k] + nums[m];
                    if (v == target) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[m]);
                        Collections.sort(list, Comparator.naturalOrder());
                        rs.add(list);
                        k++;
                        m--;
                    } else if (v > target) {
                        m--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return new ArrayList<>(rs);
    }

    private List<List<Integer>> method1(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        HashSet<List<Integer>> rs = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            int v1 = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                int v2 = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int v3 = nums[k];
                    int t = target - v1 - v2 - v3;
                    if (map.containsKey(t)) {
                        int index = map.get(t);
                        if (index != i && index != j && index != k) {
                            List<Integer> list = Arrays.asList(v1, v2, v3, t);
                            Collections.sort(list, Comparator.naturalOrder());
                            rs.add(list);
                        }
                    }

                }
            }
        }
        return new ArrayList<>(rs);
    }
}
