package clockIn;

import java.util.ArrayList;

/**
 * 面试题62. 圆圈中最后剩下的数字
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */
public class 面试题62 {
    //总结一下反推的过程，就是 (当前index + m) % 上一轮剩余数字的个数。
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans += (ans + m) % i;
        }
        return ans;
    }

    public int lastRemainingArrayList(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

}
