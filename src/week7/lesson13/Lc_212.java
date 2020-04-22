package week7.lesson13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 212. 单词搜索 II
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * <p>
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * <p>
 * 提示:
 * <p>
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 */
public class Lc_212 {
    HashSet<String> set = new HashSet<>();
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
//        char[][] boad = new char[][]
//                {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
//        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        char[][] boad = {{'a', 'b'}, {'c', 'd'}};
        String[] words = {"abcd"};
        List<String> list = new Lc_212().findWords(boad, words);
        System.out.println(list);
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (words == null || words.length == 0) return new ArrayList();
        // 将所有的字符串，放在trie树
        Trie trie = new Trie();
        Arrays.stream(words).forEach(trie::insert);
        //开始判断boad
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, visited, i, j, "", n, m, trie);
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, String s, int n, int m, Trie trie) {
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j]) return;
        s += board[i][j];
        if (!trie.startsWith(s)) return;
        if (trie.searchWord(s)) set.add(s);
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int dx_i = dx[k] + i;
            int dx_j = dy[k] + j;
            dfs(board, visited, dx_i, dx_j, s, n, m, trie);
        }
        //   dfs(board, visited, i-1, j, s, n, m, trie );
        // dfs(board, visited, i+1, j, s, n, m, trie );
        // dfs(board, visited, i, j-1, s, n, m, trie );
        // dfs(board, visited, i, j+1, s, n, m, trie );
        visited[i][j] = false;

    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.containKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setEnd();
        }

        public boolean searchWord(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        public boolean startsWith(String word) {
            TrieNode node = searchPrefix(word);
            return node != null;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.containKey(c)) {
                    node = node.get(c);
                } else {
                    return null;
                }
            }
            return node;
        }
    }

    class TrieNode {
        private final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }

    }
}