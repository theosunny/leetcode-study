package week3.lesson6;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其后序遍历: [5,6,3,2,4,1].
 */
public class LeetCode_590_0227 {


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

    ArrayList<Integer> res = new ArrayList<>();

    //递归法
    public List<Integer> postorder(Node root) {
        if (root == null) return res;
        recurse(root);
        return res;
    }

    public void recurse(Node root) {
        if (root == null) return;
        for (Node node : root.children) {
            recurse(node);
        }
        res.add(root.val);
    }

    //迭代法
    public List<Integer> postorderLoop(Node root) {
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) return output;
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for(Node n:node.children){
                if (n!=null){
                    stack.add(n);
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        stack.add(10);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.add(4);
        stack.addLast(5);
        System.out.println(stack.get(1));
        System.out.println(stack.getLast());
        System.out.println(stack.getFirst());
    }
}