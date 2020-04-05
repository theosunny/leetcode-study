package recurse;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class LeetCode_98_验证二叉搜索树 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        //递归
//        return Method1(root);
        //深度优先
//        return Method2(root);
        //利用中序遍历的有序性  dfs
//        return Method3(root);
        //利用中序遍历的有序性  recurse
        return Method4(root);
    }

    private double pre = -Double.MAX_VALUE;

    private boolean Method4(TreeNode root) {
        if (root == null) return true;
        return Inorder(root);
    }

    private boolean Inorder(TreeNode root) {
        if (root == null) return true;
        if (!Inorder(root.left)) return false;
        if (root.val <= pre) return false;
        else pre = root.val;
        if (!Inorder(root.right)) return false;
        return true;
    }

    private boolean Method3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    private boolean Method2(TreeNode root) {
        if (root == null) return true;
        Integer lower = null, upper = null;
        update(root, lower, upper);
        while (!stack.isEmpty()) {
            root = stack.pop();
            upper = uppers.pop();
            lower = lowers.pop();
            if (root == null) continue;
            int val = root.val;
            if (lower != null && lower >= val) return false;
            if (upper != null && upper <= val) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    private void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        uppers.add(upper);
        uppers.add(lower);
    }

    private boolean Method1(TreeNode root) {

        return helper(root, null, null);
    }

    /**
     * @param root
     * @param lower 上界
     * @param upper 下界
     */
    private boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        int val = root.val;
        //process
        if (lower != null && lower >= val) return false;
        if (upper != null && upper <= val) return false;
        if (!helper(root.left, lower, val)) return false;
        if (!helper(root.right, val, upper)) return false;

        return true;
    }
}
