package week4;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * <p>
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class LeetCode_102_0227 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
//        return method1(root);
//        return method2(root);
        return method3(root);
    }
    //dfs
    private List<List<Integer>> method3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (level == res.size()) res.add(new ArrayList<Integer>());
        //填充当前层
        res.get(level).add(root.val);
        if (root.left != null) {
            helper(res, root.left, level + 1);
        }
        if (root.right != null) {
            helper(res, root.right, level + 1);
        }
    }

    private List<List<Integer>> method2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> data = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                data.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(data);
        }
        return res;
    }

    private List<List<Integer>> method1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            //一个临时list存放下一层node
            LinkedList<TreeNode> treeNodeTmp = new LinkedList<>();
            ArrayList<Integer> curList = new ArrayList<>();
            for (int i = 0; i < treeNodes.size(); i++) {
                TreeNode node = treeNodes.get(i);
                curList.add(node.val);
                if (node.left != null) treeNodes.add(node.left);
                if (node.right != null) treeNodes.add(node.right);
            }
            treeNodes = treeNodeTmp;
            res.add(curList);
        }
        return res;
    }

}
