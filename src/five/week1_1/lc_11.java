package five.week1_1;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * <p>
 * <p>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class lc_11 {
    public int maxArea(int[] height) {
//        return method1(height);
        return method2(height);
    }

    private int method2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else right--;

        }
        return max;
    }

    private int method3(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max += Math.max(max,Math.min(height[l],height[r])*(r-l));
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
        }
        return max;
    }

    private int method1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int min = Integer.MIN_VALUE;
            for (int j = i + 1; j < height.length; j++) {
                min = Math.min(height[i], height[j]);
                max = Math.max(max, min * (j - i));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new lc_11().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
