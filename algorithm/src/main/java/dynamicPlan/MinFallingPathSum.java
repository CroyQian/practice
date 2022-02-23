package dynamicPlan;

/**
 * @author Croy Qian
 * @createDate 2021/6/21
 * @Description
 */
public class MinFallingPathSum {
    /**
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     * <p>
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     *
     * @param matrix
     * @return
     * @see <a href="https://leetcode-cn.com/problems/minimum-falling-path-sum/"></a>
     */
    public int minFallingPathSum1(int[][] matrix) {
        int len = matrix.length;
        if (len == 1) {
            return matrix[0][0];
        }
        int[][] dp = new int[len][len];
        for (int k = 0; k < len; k++) {
            dp[0][k] = matrix[0][k];
        }
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];
            for (int j = 1; j < len - 1; j++) {
                int temp = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                temp = Math.min(temp, dp[i - 1][j + 1]);
                dp[i][j] = temp + matrix[i][j];
            }
            dp[i][len - 1] = Math.min(dp[i - 1][len - 2], dp[i - 1][len - 1]) + matrix[i][len - 1];
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < len; j++) {
            result = Math.min(result, dp[len - 1][j]);
        }
        return result;
    }

    public int minFallingPathSum2(int[][] matrix) {
        int len = matrix.length;
        if (len == 1) {
            return matrix[0][0];
        }
        int[][] dp = new int[2][len];
        for (int k = 0; k < len; k++) {
            dp[0][k] = matrix[0][k];
        }
        for (int i = 1; i < len; i++) {
            dp[i & 1][0] = Math.min(dp[(i - 1) & 1][0], dp[(i - 1) & 1][1]) + matrix[i][0];
            for (int j = 1; j < len - 1; j++) {
                int temp = Math.min(dp[(i - 1) & 1][j - 1], dp[(i - 1) & 1][j]);
                temp = Math.min(temp, dp[(i - 1) & 1][j + 1]);
                dp[i & 1][j] = temp + matrix[i][j];
            }
            dp[i & 1][len - 1] = Math.min(dp[(i - 1) & 1][len - 2], dp[(i - 1) & 1][len - 1]) + matrix[i][len - 1];
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < len; j++) {
            result = Math.min(result, dp[(len - 1) & 1][j]);
        }
        return result;
    }

    /**
     * 进阶问题：
     * 给你一个整数方阵 arr ，定义「非零偏移下降路径」为：从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
     * 请你返回非零偏移下降路径数字和的最小值。
     *
     * @param arr
     * @return
     * @see <a href="https://leetcode-cn.com/problems/minimum-falling-path-sum-ii/"></a>
     */
    public int minFallingPathSumEx1(int[][] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr[0][0];
        }
        int[][] dp = new int[len][len];
        for (int k = 0; k < len; k++) {
            dp[0][k] = arr[0][k];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < len; k++) {
                    if (k != j) {
                        min = Math.min(min, dp[i - 1][k]);
                    }
                }
                dp[i][j] = arr[i][j] + min;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < len; j++) {
            result = Math.min(result, dp[len - 1][j]);
        }
        return result;
    }
}
