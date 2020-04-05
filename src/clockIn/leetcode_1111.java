package clockIn;

public class leetcode_1111 {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int idx = 0;
        for (char c : seq.toCharArray()) {
            ans[idx++] = c == '(' ? idx & 1 : (idx + 1) & 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(1&1);
        System.out.println((4)&1);
    }
}
