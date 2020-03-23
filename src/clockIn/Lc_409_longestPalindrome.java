package clockIn;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class Lc_409_longestPalindrome {

    public int longestPalindrome(String s) {
        // 找出可以构成最长回文串的长度
        int[] arr = new int[128];
        for (char i : s.toCharArray()) {
            arr[i]++;
        }
        int count = 0;
        for (int i : arr) {
            count += i % 2;
        }
        return count == 0 ? s.length() : s.length() - count + 1;
    }

    /**
     * 暴力依次取出字符串然后比较
     *
     * @param s
     * @return
     */
    public int method1(String s) {
        int max = Integer.MIN_VALUE;
        char[] arr = s.toCharArray();
        ArrayDeque<Character> list = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                list.add(arr[j]);
                max = Math.max(max, calculateMax(list));
                System.out.println("max:" + max);
            }
            list.clear();
        }
        return max;
    }

    private int calculateMax(ArrayDeque<Character> arr) {
        ArrayDeque<Character> list = new ArrayDeque<>(arr);
        int size = list.size();
        if (list.size() == 1) return 1;
        while (!list.isEmpty() && list.getLast() == list.getFirst()) {
            if (list.size() > 1) {
                list.removeFirst();
                list.removeLast();
            } else {
                list.remove();
            }
        }
        if (!list.isEmpty()) return 0;
        return size;
    }

    public static void main(String[] args) {
        int abccccdd = new Lc_409_longestPalindrome().longestPalindrome("abccccdd");
        System.out.println(abccccdd);
        System.out.println("-------------------");
        ArrayDeque<Character> list = new ArrayDeque<>();
        list.add('a');
        list.add('b');
        list.add('b');
        list.add('a');
        System.out.println(new Lc_409_longestPalindrome().calculateMax(list));
        int[] a = new int[]{1, 2, 3};
        for (int i = 0; i < a.length; i++) {
            a[i]++;
        }
        System.out.println();
    }

}
