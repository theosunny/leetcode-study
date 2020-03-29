package week3.lesson6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * <p>
 * <p>
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class LeetCode_589_0227 {
    /*
        // Definition for a Node.*/
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
    }

    public List<Integer> preorder(Node root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        recurse(root, list);
        return list;
    }

    public void recurse(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (Node item : root.children) {
                recurse(item, list);
            }
        }
    }

    //迭代法
    public List<Integer> preorderLoop(Node root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node head = stack.pollLast();
            list.add(head.val);
            Collections.reverse(head.children);
            for (Node n : head.children) {
                stack.add(n);
            }

        }
        return list;
    }
}
