package dfs;

import java.util.Arrays;

/**
 * @author Croy Qian
 * @createDate 2021/8/23
 * @Description 出界的路径数
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 * @see <a href="https://leetcode-cn.com/problems/out-of-boundary-paths/"></a>
 */
public class OutOfBoundaryPaths {
    // 四个方向
    int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 取余
    int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        /**
         * 缓存,用缓存是因为可能多次经过一个点，比如从a到b先往上走再往下，和往下再往上，一样的步数
         * |__ __|
         * a     b
         * |__ __|
         */
        int[][][] memo = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return dfs(m, n, maxMove, startRow, startColumn, memo);
    }

    private int dfs(int m, int n, int moveCount, int i, int j, int[][][] memo) {
        // 越界了就找到了一条路径
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 1;
        }
        // 没有移动次数了，返回0
        if (moveCount == 0) {
            return 0;
        }
        // 缓存中存在
        if (memo[i][j][moveCount] != -1) {
            return memo[i][j][moveCount];
        }
        // 剪枝：如果小球不管怎么移动都无法越出网格，那就剪掉这个枝
        if (i - moveCount >= 0 && j - moveCount >= 0 && i + moveCount < m && j + moveCount < n) {
            return 0;
        }
        int sum = 0;
        for (int[] dir : dirs) {
            sum = (sum + dfs(m, n, moveCount - 1, i + dir[0], j + dir[1], memo)) % MOD;
        }
        // 记录缓存
        memo[i][j][moveCount] = sum;
        return sum;
    }
}
