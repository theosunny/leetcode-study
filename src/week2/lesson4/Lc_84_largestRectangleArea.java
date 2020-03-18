package week2.lesson4;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
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
public class Lc_84_largestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        return method1(heights);
    }

    /**
     * 暴力解法
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     * 枚举条形图的间隔，往中间逼近，求2个柱子间的最小高度 * 距离
     * 解法是：从两边开始计算0-1之间的最小高度同时乘以距离
     * 1、0-1之间的最小高度同时乘以距离
     * 2、0-2之间的最小高度同时乘以距离
     * 3、0-3之间的最小高度同时乘以距离
     * ....
     * i+1=>计算1-1，1-2，1-3...依次类推，逐渐计算
     *
     * @param heights
     * @return
     */
    private int method1(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    minHeight = Math.min(minHeight, heights[k]);
                }
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /**
     * 直接计算 i=0 开始，依次的最小高度，把i当成左指针逼近右边
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
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /**
     * 立中心点往两边扩散
     *
     * @param heights
     * @return
     */
    public int method3(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int v = heights[i];
            int left = i;
            // 找左边最后 1 个大于等于 heights[i] 的下标
            while (left > 0 && heights[left - 1] >= v) {
                left--;
            }
            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < heights.length - 1 && heights[right + 1] >= v) {
                right++;
            }
            maxArea = Math.max(maxArea, (right - left + 1) * v);
        }
        return maxArea;
    }

    /**
     * 栈实现
     *
     * @param heights
     * @return
     */
    private int method4(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            //如果栈内的最顶层值大于循环传入的值，则说明最顶层的右界是确定的，则其右边界是当前循环索引、左边界是其前一个元素
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek()!=-1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));

        }
        return maxArea;
    }

    /**
     * 分治分解如干个子问题
     *
     * @param heights
     * @return
     */
    private int method5(int[] heights) {
        return divide(heights, 0, heights.length - 1);
    }

    private int divide(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <=end; i++) {
            if (heights[minIndex] > heights[i]) minIndex = i;
        }
        return Math.max(heights[minIndex] * (end - start + 1), Math.max(divide(heights, start, minIndex - 1), divide(heights, minIndex + 1, end)));

    }

    public static void main(String[] args) {
//        int[] arr = {5, 6, 2, 1, 2, 3};
        int[] arr = {6, 4, 5, 2, 4, 3, 9};
        int area = new Lc_84_largestRectangleArea().method5(arr);
        System.out.println(area);
    }
}
