package five.week1_1;

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
public class lc_70 {
    public int climbStairs(int n) {
        return method1(n);
    }

    Map<Integer, Integer> m = new HashMap();

    private int method1(int n) {
        if (n == 1 || n == 2) return n;
        if (m.containsKey(n)) {
            return m.get(n);
        }
        int m1 = method1(n - 1) + method1(n - 2);
        m.put(n, m1);
        return m1;
    }

    private int method2(int n) {
        if (n == 1 || n == 2) return n;
        int t1 = 1;
        int t2 = 2;
        int t3 = 3;
        for (int i = 3; i <= n; i++) {
            t3 = t1 + t2;
            t1 = t2;
            t2 = t3;
        }
        return t2;
    }

    public static void main(String[] args) {
        System.out.println(new lc_70().method2(4));
    }
}
