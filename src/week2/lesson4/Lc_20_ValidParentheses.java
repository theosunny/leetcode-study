package week2.lesson4;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
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
public class Lc_20_ValidParentheses {
    public boolean isValid(String s) {
        return method2(s);
    }

    private boolean method2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') stack.add(')');
            else if (array[i] == '[') stack.add(']');
            else if (array[i] == '{') stack.add('}');
            else if (stack.isEmpty() || stack.pop() != array[i]) return false;
        }
        return stack.isEmpty();
    }

    //直接替换括号对
    public boolean method1(String s) {
        while (s != null && s.length() > 0) {
            if (s.contains("()")) s = s.replace("()", "");
            else if (s.contains("[]")) s = s.replace("[]", "");
            else if (s.contains("{}")) s = s.replace("{}", "");
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean valid = new Lc_20_ValidParentheses().isValid("{}}");
        System.out.println(valid);
    }
}
