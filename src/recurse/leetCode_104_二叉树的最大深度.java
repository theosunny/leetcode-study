package recurse;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class leetCode_104_二叉树的最大深度 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return helper(root);
    }

    private int helper(TreeNode root) {
        //terminator
        if (root == null) return 0;
        //process
        int v = 0, v1 = 0;
        if (root.left != null) {
            v = helper(root.left);
        }
        if (root.right != null) {
            v1 = helper(root.right);
        }

        //drill down

        //reverse;
        return Math.max(v, v1) + 1;


    }
}
