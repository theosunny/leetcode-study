package week2.lesson4.ex2;

import week2.lesson4.ex1.Lc_84_ex;

import java.util.Stack;

public class Lc_84 {
    public int largestRectangleArea(int[] heights) {
        return method1(heights);
    }

    private int method1(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    minHeight = Math.min(minHeight, heights[k]);
                }
                maxarea = Math.max(maxarea, minHeight * (j - 1 + 1));
            }
        }
        return maxarea;
    }

    private int method2(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxarea = Math.max(maxarea, minHeight * (j - i + 1));
            }
        }
        return maxarea;
    }

    private int method3(int[] heights) {
        int maxarea = 0;
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
            maxarea = Math.max(maxarea, v * (right - left + 1));
        }
        return maxarea;
    }

    private int method4(int[] heights) {
        return divide(heights, 0, heights.length - 1);
    }

    public int divide(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++) minIndex = heights[minIndex] > heights[i] ? i : minIndex;
        return Math.max(heights[minIndex] * (end - start + 1), Math.max(divide(heights, start, minIndex - 1), divide(heights, minIndex + 1, end)));
    }

    private int method5(int[] heights) {
        int maxarea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                maxarea = Math.max(maxarea, stack.pop() * (i - stack.peek() - 1));

            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxarea;
    }

    public static void main(String[] args) {
        //        int[] arr = {5, 6, 2, 1, 2, 3};
        int[] arr = {6, 4, 5, 2, 4, 3, 9};
        int area = new Lc_84().method5(arr);
        System.out.println(area);
    }
}
