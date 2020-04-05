package recurse;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class leetcode70_爬楼梯 {
    public int climbStairs(int n) {
        //递归
//        return method1(n);
        //迭代
        return method2(n);
    }

    private int method2(int n) {
        if (n <= 2) return n;
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        for (int i = 3; i <= n; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n3;

    }

    Map<Integer, Integer> map = new HashMap<>();

    private int method1(int n) {
        if (n <= 2) return n;
        if (map.containsKey(n)) return map.get(n);
        int d1 = method1(n - 1);
        int d2 = method1(n - 2);
        map.put(n - 1, d1);
        map.put(n - 2, d2);
        return d1 + d2;
    }
}
