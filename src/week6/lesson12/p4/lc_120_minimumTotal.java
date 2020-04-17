package week6.lesson12.p4;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class lc_120_minimumTotal {
    //自底向上dp
    public int minimumTotal(List<List<Integer>> triangle) {
        //dp 方程 f[i][j] = f[i][j] + min(f[i+1][j],f[i+1][j+1])
        int[] res = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

    int row;

    //递归实现 自顶向下
    public int method2(List<List<Integer>> triangle) {
        row = triangle.size();
        return helper(0, 0, triangle);
    }

    private int helper(int level, int c, List<List<Integer>> triangle) {
        if (level == row - 1) {
            return triangle.get(level).get(c);
        }
        int left = helper(level + 1, c, triangle);
        int right = helper(level + 1, c + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(c);
    }

    Integer[][] memo;

    //递归实现 同时优化，增加记忆化 自顶向下
    public int method3(List<List<Integer>> triangle) {
        row = triangle.size();
        memo = new Integer[row][row];
        return helper2(0, 0, triangle);
    }

    private int helper2(int level, int c, List<List<Integer>> triangle) {

        if (memo[level][c] != null) {
            return memo[level][c];
        }
        if (level == row - 1) {
            return memo[level][c] = triangle.get(level).get(c);
        }
        int left = helper2(level + 1, c, triangle);
        int right = helper2(level + 1, c + 1, triangle);
        return memo[level][c] = Math.min(left, right) + triangle.get(level).get(c);
    }

}
