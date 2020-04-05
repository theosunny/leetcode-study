package week4.lesson11;

/**
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * <p>
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * <p>
 * 示例 1：
 * <p>
 * 输入：16
 * 输出：True
 * 示例 2：
 * <p>
 * 输入：14
 * 输出：False
 */
public class LeetCode_367_0227 {
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        long l = 0, r = num / 2;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public boolean mysqrtNd(int num) {
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }

    public static void main(String[] args) {
        boolean perfectSquare = new LeetCode_367_0227().isPerfectSquare(14);
        System.out.println(perfectSquare);
    }
}
