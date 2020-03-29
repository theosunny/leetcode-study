package week3.lesson6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode_144_0227 {
    List res = new ArrayList<Integer>();

    //前序遍历，先遍历根节点，左节点，右节点
    public List<Integer> preorderTraversal(TreeNode root) {
        recurse(root);
        return res;
    }

    public void recurse(TreeNode root) {
        if (root != null) {
            res.add(root.val);
            if (root.left != null) recurse(root.left);
            if (root.right != null) recurse(root.right);
        }
    }

    public List<Integer> preorderTraversalByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }
        return res;
    }

    //先序遍历 莫里斯法
    public List<Integer> preorderTraversalByMorris(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                res.add(node.val);
                node = node.left;
            } else {
                TreeNode predecessor = node.left;
                while (predecessor != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    res.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }

        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }
}
