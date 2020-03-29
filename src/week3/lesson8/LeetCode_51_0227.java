package week3.lesson8;

import java.util.*;

public class LeetCode_51_0227 {
    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return res;
        Set<Integer> cols = new HashSet<>();
        Set<Integer> na = new HashSet<>();
        Set<Integer> pie = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        backstrack(nums, n, 0, cols, pie, na, stack);
        return res;
    }

    private void backstrack(int[] nums, int n, int row, Set<Integer> cols, Set<Integer> pie, Set<Integer> na, Stack<Integer> stack) {
        if (row == n) {
            List<String> list = print(stack, n);
            res.add(list);
            return;
        }
        // 针对每一列，尝试是否可以放置
        for (int i = 0; i < n; i++) {
            if (cols.contains(i) || pie.contains(row + i) || na.contains(row - i)) {
                continue;
            }
            stack.add(nums[i]);
            cols.add(i);
            pie.add(row + i);
            na.add(row - i);
            backstrack(nums, n, row + 1, cols, pie, na, stack);
            cols.remove(i);
            pie.remove(row + i);
            na.remove(row - i);
            stack.pop();
        }
    }

    private List<String> print(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : stack) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                builder.append(".");
            }
            builder.replace(num, num + 1, "Q");
            board.add(builder.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        List<List<String>> list = new LeetCode_51_0227().solveNQueens(8);
        System.out.println(list);
//        LeetCode_51_0227 o = new LeetCode_51_0227();

    }


}
