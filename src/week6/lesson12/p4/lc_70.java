package week6.lesson12.p4;

public class lc_70 {

    public int climbStairs(int n) {
        if (n < 3) return n;
        int one = 1;
        int two = 2;
        int three = 3;
        for (int i = 3; i <= n; i++) {
            three = one + two;
            one = two;
            two = three;
        }
        return two;
    }

    //dp
    public int method2(int n) {
        if (n < 3) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
