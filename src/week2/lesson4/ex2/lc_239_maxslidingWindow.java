package week2.lesson4.ex2;


public class lc_239_maxslidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return method1(nums, k);
    }

    //暴力解法
    private int[] method1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        //initialize return array size = n-k+1;
        int[] arr = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            arr[i] = max;
        }
        return arr;
    }

    public static void main(String[] args) {
        //* 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        // * 输出: [3,3,5,5,6,7]
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr = new lc_239_maxslidingWindow().method1(nums, 3);
        for (int a = 0; a < arr.length; a++) {
            System.out.println(arr[a]);
        }
    }
}
