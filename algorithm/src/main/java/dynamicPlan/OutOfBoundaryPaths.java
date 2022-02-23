package dynamicPlan;

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
        int[][][] dp = new int[m][n][maxMove + 1];
        // 移动步数2的都是从移动步数1的转移来的
        // 移动步数3的都是从移动步数2的转移来的
        // 所以，要从移动步数从1开始递增
        for (int k = 1; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 处理四条边
                    if (i == 0) dp[i][j][k]++;
                    if (j == 0) dp[i][j][k]++;
                    if (i == m - 1) dp[i][j][k]++;
                    if (j == n - 1) dp[i][j][k]++;

                    // 中间的位置，向四个方向延伸
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n) {
                            dp[i][j][k] = (dp[i][j][k] + dp[nextI][nextJ][k - 1]) % MOD;
                        }
                    }
                }
            }
        }
        return dp[startRow][startColumn][maxMove];
    }

    public int findPathsEx(int m, int n, int maxMove, int startRow, int startColumn) {
        int[] dp = new int[m * n];
        // 移动步数2的都是从移动步数1的转移来的
        // 移动步数3的都是从移动步数2的转移来的
        // 所以，要从移动步数从1开始递增
        for (int k = 1; k <= maxMove; k++) {
            // 需要声明一个临时数组
            // 比如计算[1,2]的时候会用到[2,2]，同时计算[2,2]的时候也会用到[1,2]
            // 这样计算[1,2]的时候就不能直接把值覆盖了，必须一轮计算完了才能覆盖
            int[] tmp = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = index(i, j, n);
                    // 处理四条边
                    if (i == 0) tmp[index]++;
                    if (j == 0) tmp[index]++;
                    if (i == m - 1) tmp[index]++;
                    if (j == n - 1) tmp[index]++;

                    // 中间的位置，向四个方向延伸
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        int nextIndex = index(nextI, nextJ, n);
                        if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n) {
                            tmp[index] = (tmp[index] + dp[nextIndex]) % MOD;
                        }
                    }
                }
            }
            dp = tmp;
        }
        return dp[index(startRow, startColumn, n)];
    }

    private int index(int i, int j, int n) {
        return i * n + j;
    }
}
