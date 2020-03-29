package clockIn;

import java.util.*;

/**
 * 820. 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 */
public class LeetCode_820_minimumLengthEncoding_官方 {
    /**
     * 暴力解法
     *
     * @param words
     * @return
     */
    public int minimumLengthEncodingForce(String[] words) {
        Set<String> set = new HashSet<String>(Arrays.asList(words));
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j < words[i].length(); j++) {
                set.remove(words[i].substring(j));
            }
        }
        int res = 0;
        for (String word : set) {
            res += word.length() + 1;
        }
        return res;
    }

    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();
        // 单词插入trie，返回该单词增加的编码长度
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; j--) {
                cur = cur.get(word.charAt(j));
            }
            nodes.put(cur, i);
        }
        int res = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.count == 0) {
                res += words[nodes.get(node)].length() + 1;
            }
        }
        return res;
    }


    class TrieNode {
        int count;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }

}