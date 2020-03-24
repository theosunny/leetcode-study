package five.week1_1;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class lc_84 {
    public int largestRectangleArea(int[] heights) {
        return method1(heights);
    }

    /**
     * 暴力解法
     *
     * @param heights
     * @return
     */
    private int method1(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    minHeight = Math.min(minHeight, heights[k]);
                }
                res = Math.max(res, minHeight * (j - i + 1));
            }
        }
        return res;
    }

    private int method2(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                res = Math.max(res, minHeight * (j - i + 1));
            }
        }
        return res;
    }

    private int method3(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int v = heights[i];
            int left = i;
            int right = i;
            while (left > 0 && heights[left - 1] >= v) {
                left--;
            }
            while (right < heights.length - 1 && heights[right + 1] >= v) {
                right++;
            }
            res = Math.max(res, v * (right - left + 1));
        }
        return res;
    }

    private int method4(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            int v = heights[i];
            while (stack.peek() != -1 && heights[stack.peek()] > v) {
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            res = Math.max(res, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return res;
    }

    /**
     * 分治
     *
     * @param heights
     * @return
     */
    private int method5(int[] heights) {
        return divide(heights, 0, heights.length - 1);
    }

    private int divide(int[] heights, int start, int end) {
        if (start > end)
            return 0;
        int minindex = start;
        for (int i = start; i < end; i++) {
            if (heights[minindex] > heights[i])
                minindex = i;
        }
        return Math.max(heights[minindex] * (end -start  + 1), Math.max(divide(heights, start, minindex - 1), divide(heights, minindex + 1, end)));
    }

    public static void main(String[] args) {
        System.out.println(new lc_84().method5(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
