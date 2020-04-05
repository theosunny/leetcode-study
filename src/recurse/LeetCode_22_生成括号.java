package recurse;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LeetCode_22_生成括号 {
    ArrayList<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        //基于模板练习，全排列
//        method1(0, n * 2, "");
        //真正的方法实现
        method2(0, 0, n, "");
        return res;
    }

    private void method2(int left, int right, int n, String s) {
        if (left >= n && right >= n) {
            res.add(s);
            return;
        }

        if (left < n) {
            method2(left + 1, right, n, s + "(");
        }
        if (right < left) {
            method2(left, right + 1, n, s + ")");
        }
    }

    private void method1(int level, int max, String s) {
        //terminator
        if (level >= max) {
            //func check invalid
            System.out.println(s);
            return;
        }
        //process current logic
        String left = s + "(";
        String right = s + ")";
        //drill down
        method1(level + 1, max, left);
        method1(level + 1, max, right);
        res.add(s);
        //revert states
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode_22_生成括号().generateParenthesis(3));
    }
}
