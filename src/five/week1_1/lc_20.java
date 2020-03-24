package five.week1_1;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class lc_20 {
    public boolean isValid(String s) {

        while (s != null && s.length() != 0) {
            if (s.contains("{}")) {
                s = s.replace("{}", "");
            } else if (s.contains("[]")) {

                s = s.replace("[]", "");
            } else if (s.contains("()")) {
                s = s.replace("()", "");
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidStack(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char v = arr[i];
            if (v == '{') {
                stack.push('}');
            } else if (v == '(') {
                stack.push(')');
            } else if (v == '[') {
                stack.push(']');
            } else if (!stack.isEmpty() && stack.pop() != v) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
