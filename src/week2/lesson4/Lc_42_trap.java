package week2.lesson4;

import java.util.Stack;

/**
 * 42. Trapping Rain Water
 * Hard
 * <p>
 * 5839
 * <p>
 * 107
 * <p>
 * Add to List
 * <p>
 * Share
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class Lc_42_trap {
    public int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                int bound_height = Math.min(height[i], height[stack.peek()]) - height[top];
                res += distance * bound_height;
            }
            stack.push(i);
        }
        return res;
    }

    public int method1(int[] height) {
        int res = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int left = 0;
            int right = 0;
            for (int j = i; j >= 0; j--) {
                left = Math.max(left, height[j]);
            }
            for (int j = i; j < size; j++) {
                right = Math.max(right, height[j]);
            }
            int res1 = Math.min(right, left) - height[i];
            res += res1;
            System.out.println("i=" + i + " res1=" + res1 + " res=" + res);
        }
        return res;
    }

    //解法一的变形，首先查询每个柱子的最大高度和最小高度
    public int method2(int[] height) {
        int res = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0]=height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 1; i >= 0; i++) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            res += Math.max(left_max[i], right_max[i]) - height[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Lc_42_trap().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Lc_42_trap().method1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
