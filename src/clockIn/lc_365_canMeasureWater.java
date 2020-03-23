package clockIn;

import java.util.*;

/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 * <p>
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class lc_365_canMeasureWater {
    //dfs
    public boolean canMeasureWater(int x, int y, int z) {
        if (y == z || x == z || x + y == z) return true;
        if (x + y < z) return false;
        Stack<int[]> stack = new Stack<>(); //存储所有可能的状态
        Set<Map.Entry<Integer, Integer>> seenSet = new HashSet<>();  // 存储处理过的状态
        stack.push(new int[]{0, 0});
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            if (arr[0] == z || arr[1] == z || arr[0] + arr[1] == z) return true;
            Map.Entry<Integer, Integer> currEntry = new AbstractMap.SimpleEntry<>(arr[0], arr[1]);
            if (seenSet.contains(currEntry)) continue; // 之前处理过这个状态，跳过
            seenSet.add(currEntry);
            //灌满X
            stack.push(new int[]{x, arr[1]});
            //灌满y
            stack.push(new int[]{arr[0], y});
            //清空X
            stack.push(new int[]{0, arr[1]});
            //清空y
            stack.push(new int[]{arr[0], y});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            if (arr[0] + arr[1] < y) {
                stack.push(new int[]{0, arr[0] + arr[1]});
            } else {
                stack.push(new int[]{arr[0] + arr[1] - y, y});
            }
            // 把y 壶的水灌进 x 壶，直至灌满或倒空。
            if (arr[0] + arr[1] < x) {
                stack.push(new int[]{arr[0] + arr[1], 0});
            } else {
                stack.push(new int[]{x, arr[0] + arr[1] - x});

            }
        }
        return false;
    }

    public boolean canMeasureWaterBFS(int x, int y, int z) {
        if (y == z || x == z || x + y == z) return true;
        if (x + y < z) return false;
        Queue<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>();
        AbstractMap.SimpleEntry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(0, 0);
        queue.add(start);
        HashSet<Map.Entry<Integer, Integer>> visited = new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            Integer currX = entry.getKey();
            Integer currY = entry.getValue();
            if (currX == z || currY == z || currX + currY == z) return true;
            //装满X
            if (currX == 0) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x, currY));
            }
            //装满Y
            if (currY == 0) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(currX, y));
            }
            //倒空第一个桶x
            if (currY < y) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, currY));
            }
            //倒空第2个桶y
            if (currX < x) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(currX, 0));
            }
            // y - curY是第二个桶还可以再加的水的升数，但是最多只能加curX升水。
            int moveSize = Math.min(y - currY, currX);
            // 把第一个桶里的curX升水倒到第二个桶里去。
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(currX - moveSize, currY + moveSize));
            // 反过来同理，x - curX是第一个桶还可以再加的升数，但是最多只能加curY升水。
            moveSize = Math.min(x - currX, currY);
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(currX + moveSize, currY - moveSize));
        }
        return false;
    }

    private void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue, HashSet<Map.Entry<Integer, Integer>> visited, AbstractMap.SimpleEntry<Integer, Integer> newEntry) {
        if (!visited.contains(newEntry)) {
            visited.add(newEntry);
            queue.add(newEntry);
        }
    }

    /**
     * 其实就是看x和y的最大公约数能否整除z，
     * 具体解释如下：
     * 若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，特别地，一定存在整数x,y，使ax+by=d成立。
     * 之后百科了一下发现这其实是裴蜀定理：
     * 如果所需要的水量是两个水壶容量的最大公约数的倍数，且水量不大于两个水壶的容量之和，那么必然可以用这两个水壶操作得到所需要的水量。
     * <p>
     * 裴蜀定理 : https://baike.baidu.com/item/%E8%A3%B4%E8%9C%80%E5%AE%9A%E7%90%86/5186593?fr=aladdin
     */
    public boolean canMeasureWaterGS(int x, int y, int z) {
        if (y == z || x == z || x + y == z) return true;
        if (x + y < z) return false;
        // 辗转相除法
        int tmp = gcd(x, y);
        return z % tmp == 0;
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);

    }
}