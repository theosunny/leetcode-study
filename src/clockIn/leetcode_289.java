package clockIn;

/**
 * 289. 生命游戏
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * 输出：
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class leetcode_289 {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        //暴力
//        method1(board);
        //优化if判断
        method2(board);
    }

    /**
     * 算法
     *
     * 遍历 board 中的细胞。
     *
     * 根据数组的细胞状态计算新一轮的细胞状态，这里会用到能同时代表过去状态和现在状态的复合状态。
     *
     * 具体的计算规则如下所示：
     *
     * 规则 1：如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡。这时候，将细胞值改为 -1，代表这个细胞过去是活的现在死了；
     *
     * 规则 2：如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活。这时候不改变细胞的值，仍为 1；
     *
     * 规则 3：如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡。这时候，将细胞的值改为 -1，代表这个细胞过去是活的现在死了。可以看到，因为规则 1 和规则 3 下细胞的起始终止状态是一致的，因此它们的复合状态也一致；
     *
     * 规则 4：如果死细胞周围正好有三个活细胞，则该位置死细胞复活。这时候，将细胞的值改为 2，代表这个细胞过去是死的现在活了。
     *
     * 根据新的规则更新数组；
     *
     * 现在复合状态隐含了过去细胞的状态，所以我们可以在不复制数组的情况下完成原地更新；
     *
     * 对于最终的输出，需要将 board 转成 0，1 的形式。因此这时候需要再遍历一次数组，将复合状态为 2 的细胞的值改为 1，复合状态为 -1 的细胞的值改为 0。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/game-of-life/solution/sheng-ming-you-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param board
     */
    private void method2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //==1是为了去掉自身
                int count = board[i][j] == 1 ? -1 : 0;
                for (int k = -1; k < 2; k++) {
                    for (int m = -1; m < 2; m++) {
                        int r = i + k;
                        int c = j + m;
                        if ((r >= 0 && r < rows) && (c < cols && c >= 0) && Math.abs(board[r][c]) == 1) {
                            count++;
                        }
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3))
                    board[i][j] = -1;

                if (board[i][j] == 0 && count == 3)
                    board[i][j] = 2;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

    }

    private void method1(int[][] board) {
        int[][] origin = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                origin[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                check(origin, board, i, j);
            }
        }
    }

    //输出
    //[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
    //预期结果
    //[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
    private void check(int[][] origin, int[][] board, int i, int j) {
        //横
        int column = origin[i].length - 1;
        int row = origin.length - 1;
        int ans = 0;
        if (j + 1 <= column) {
            ans += origin[i][j + 1];
        }
        if (j - 1 >= 0) {
            ans += origin[i][j - 1];
        }

        //竖
        if (i - 1 >= 0) {
            ans += origin[i - 1][j];
        }
        if (i + 1 <= row) {
            ans += origin[i + 1][j];
        }
        //对角线
        if (i - 1 >= 0 && j - 1 >= 0) {
            ans += origin[i - 1][j - 1];
        }
        if (i - 1 >= 0 && j + 1 <= column) {
            ans += origin[i - 1][j + 1];
        }
        if (i + 1 <= row && j - 1 >= 0) {
            ans += origin[i + 1][j - 1];
        }
        if (i + 1 <= row && j + 1 <= column) {
            ans += origin[i + 1][j + 1];
        }
        if (origin[i][j] == 1) {
            if (ans < 2 || ans > 3) board[i][j] = 0;
        }
        if (origin[i][j] == 0) {
            if (ans == 3) board[i][j] = 1;
        }
    }

    public static void main(String[] args) {
        int[][] array = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        new leetcode_289().gameOfLife(array);
        System.out.println(array);
    }
}
