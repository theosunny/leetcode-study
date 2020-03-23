package week2.lesson5;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class lc_49_groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        return method1(strs);
    }

    /**
     * 基础解法，满足各种场景，就是速度过慢
     *
     * @param strs
     * @return
     */
    private List<List<String>> method1(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        List<List<String>> rs = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            map.put(i, strs[i]);
        }
        for (int i = 0; i < strs.length; i++) {
            if (!map.containsKey(i)) continue;
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagrams(strs[i], strs[j])) {
                    list.add(strs[j]);
                    map.remove(j);
                }
            }
            rs.add(list);
        }
        return rs;
    }

    /**
     * 排序解法
     *
     * @param strs
     * @return
     */
    private List<List<String>> method2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] s_arr = strs[i].toCharArray();
            Arrays.sort(s_arr);
            String key = String.valueOf(s_arr);
            if (map.containsKey(key)) {
                map.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(key, temp);
            }
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 算术基本定理，将字符串映射到成一个数字，真的是很好的思维
     * https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/ 原贴
     *
     * @param strs
     * @return
     */
    private List<List<String>> method3(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<List<String>> rs = new ArrayList<>();

        //每个字母对应一个质数
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(key, temp);
            }
        }
        return new ArrayList<>(map.values());
    }

    public boolean isAnagrams(String t1, String t2) {
        if (t1.length() != t2.length()) return false;
        int[] arr = new int[26];
        for (int i = 0; i < t1.length(); i++) {
            arr[t1.charAt(i) - 'a']++;
            arr[t2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean b = new lc_49_groupAnagrams().isAnagrams("", "");
        System.out.println(b);
//        List<List<String>> rs = new lc_49_groupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        List<List<String>> rs = new lc_49_groupAnagrams().groupAnagrams(new String[]{"", ""});
        System.out.println(rs);
    }
}
