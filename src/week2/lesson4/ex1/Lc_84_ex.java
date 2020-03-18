package week2.lesson4.ex1;


import week2.lesson4.Lc_84_largestRectangleArea;

import java.util.Stack;

/**
 * 五遍刷提第一步
 * 最大矩形问题
 */
public class Lc_84_ex {
    public int largestRectangleArea(int[] heights) {
        return method1(heights);
    }

    /**
     * 暴力解法，2个指针求i,j之间的最小高度，算出面积
     *
     * @param heights
     * @return
     */
    private int method1(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minHeight = Integer.MAX_VALUE;
                //求i和j之间的最大面积
                for (int k = i; k <= j; k++) {
                    minHeight = Math.min(minHeight, heights[k]);
                }
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /**
     * 其实method1多了一份循环，可以直接计算出最小高度
     *
     * @param heights
     * @return
     */
    private int method2(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
            }
        }
        return maxArea;
    }

    /**
     * 双指针的变形
     *
     * @param heights
     * @return
     */
    private int method3(int[] heights) {
        int maxArea = 0;
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
            maxArea = Math.max(maxArea, v * (right - left + 1));
        }
        return maxArea;
    }

    /**
     * 神tm分治
     * 步骤，先找出第一次循环的最小柱子，
     * 则可以将此问题分解成，
     * 1、计算最小柱子左边的面积的最大值，或者
     * 2、计算最小柱子右边的面积的最大值，或者
     * 3、当前最小柱子的面积
     *
     * @param heights
     * @return
     */
    private int method4(int[] heights) {
        return divide(heights, 0, heights.length - 1);
    }

    private int divide(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[minIndex] > heights[i]) minIndex = i;
        }
        return Math.max(heights[minIndex] * (end - start + 1), Math.max(divide(heights, start, minIndex - 1), divide(heights, minIndex + 1, end)));
    }

    /**
     * 栈解决
     *
     * @param heights
     * @return
     */
    private int method5(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        //        int[] arr = {5, 6, 2, 1, 2, 3};
        int[] arr = {6, 4, 5, 2, 4, 3, 9};
        int area = new Lc_84_ex().method5(arr);
        System.out.println(area);
    }
}
