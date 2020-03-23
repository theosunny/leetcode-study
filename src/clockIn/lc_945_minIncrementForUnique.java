package clockIn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * <p>
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 * <p>
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 */
public class lc_945_minIncrementForUnique {
    //解法超时
    public int minIncrementForUnique(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < A.length; i++) {
//            map.put(A[i], i);
//        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            int v = A[i];
            while (map.containsKey(v) && map.get(v) != i) {
                v = v + 1;
                sum++;
            }
            map.put(v, i);
        }
        return sum;
    }

    public int method1(int[] A) {
        //排序
        Arrays.sort(A);
        int move = 0;
        // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数+1
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;
    }

    public int method2(int[] A) {
        //排序
        int[] counter = new int[40001];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            counter[A[i]]++;
            max = Math.max(max, A[i]);
        }
        int move = 0;
        for (int i = 0; i <= max; i++) {
            if (counter[i] > 1) {
                int d = counter[i] - 1;
                move += d;
                counter[i + 1] += d;
            }
        }
        int d = counter[max + 1] - 1;
        move += (d + 1) * d / 2;
        return move;
    }

    /**
     * 开放寻址法
     *
     * @param A
     * @return
     */
    int[] pos = new int[80000];

    public int method3(int[] A) {
        //先填-1，初始化数组
        Arrays.fill(pos, -1);
        int move = 0;
        for (int a : A) {
            //开放寻址
            int b = findPos(a);
            move += b - a;
        }
        return move;
    }

    private int findPos(int a) {
        int b = pos[a];
        //如果pos为空位则新增数据
        if (b == -1) {
            pos[a] = a;
            return a;
        }
        //否则在当前位置上+1，寻找下一个位置，因为已经记住上次的位置，所以是值+1’
        b = findPos(b + 1);
        //寻址后赋值回来，方便下一次查询寻址，路径压缩就体现在这里
        pos[a] = b;
        return b;
    }

    public static void main(String[] args) {
//        int i = new lc_945_minIncrementForUnique().minIncrementForUnique(new int[]{1, 2, 2});
        int i = new lc_945_minIncrementForUnique().method1(new int[]{3, 2, 1, 2, 1, 7});
        System.out.println(i);
    }

    public int method3Ex(int[] A) {
        int move = 0;
        Arrays.fill(pos, -1);
        for (int i = 0; i < A.length; i++) {
            int v = A[i];
            int a = findPosEx(v);
            move += a-v;
        }
        return move;
    }

    private int findPosEx(int v) {
        int a = pos[v];
        if (a == -1) {
            pos[v] = v;
            return v;
        }
        int b = findPosEx(a + 1);
        pos[v] = b;
        return b;
    }
}
