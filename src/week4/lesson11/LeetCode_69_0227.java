package week4.lesson11;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * 通过次数110,885提交次数294,692
 */
public class LeetCode_69_0227 {
    public int mySqrt(int x) {
        long left = 1, right = x;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) right;
    }

    int mySqrtNd(int x) {
        int r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return r;
    }
}
