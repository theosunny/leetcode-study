package week3.lesson6;

import javax.swing.text.LabelView;
import java.util.*;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * <p>
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 */
public class LeetCode_429_0227 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> _children) {
            this.val = val;
            children = _children;
        }

        //层序遍历，一般采用额外空间记录每一层级，循环向下
        public List<List<Integer>> levelOrder(Node root) {
            LinkedList<List<Integer>> rs = new LinkedList<>();
            Queue<Node> queue = new LinkedList<>();
            //第一层
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> levelList = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    levelList.add(node.val);
                    queue.addAll(node.children);
                }
                rs.add(levelList);

            }
            return rs;
        }
    }

    //简化版
    public List<List<Integer>> levelOrderMethod1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);
        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }
        return result;
    }

    /**
     * 递归解法，首先要明确判断返回条件level
     *
     * @param root
     * @return
     */
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrderRecurse(Node root) {
        if (root != null) getLevel(root, 0);
        return result;
    }

    public void getLevel(Node root, int level) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        for (Node node : root.children) {
            getLevel(node, level + 1);
        }
    }
}
