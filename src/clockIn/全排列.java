package clockIn;

import java.util.LinkedList;
import java.util.List;

public class 全排列 {
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        backstrack(nums, list);
        return res;
    }

    //路径 记录在track里
    //选择列表，nums中不存在track的元素
    //结束条件：nums中的元素全部在track出现
    private void backstrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])) continue;
            //做选择
            track.add(nums[i]);
            //进入下一层决策树
            backstrack(nums, track);
            //取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new 全排列().permute(new int[]{1, 2, 3});
//        permute.forEach(data->data.forEach(System.out::println));
        permute.forEach(System.out::println);
    }
}
