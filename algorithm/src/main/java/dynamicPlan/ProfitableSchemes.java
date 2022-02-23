package dynamicPlan;

/**
 * @author Croy Qian
 * @createDate 2021/6/9
 * @Description 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * @see <a href="https://leetcode-cn.com/problems/profitable-schemes/"></a>
 */
public class ProfitableSchemes {
    /**
     * 动态规划
     * 本题与经典背包问题非常相似。两者不同点在于经典背包问题只有一种容量限制，而本题却有两种限制：集团员工人数上限 nn，以及工作产生的利润下限 minProfit。
     * 通过经典背包问题的练习，我们已知经典背包问题可以使用二维动态规划求解：两个维度分别代表物品和容量的限制标准。对于本题上述的两种限制，我们可以想到使用三维动态规划求解。本题解法的三个维度分别为：当前可选择的工作，已选择的小组员工人数，以及目前状态的工作获利下限。
     * 根据上述分析，我们可以定义一个三维数组 dp 作为动态规划的状态，其中 dp[i][j][k] 表示在前 i 个工作中选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目。
     * 对于每个工作 i，我们根据当前工作人数上限 j，有能够开展当前工作和无法开展当前工作两种情况：
     * 如果无法开展当前工作 i，那么显然：
     * dp[i][j][k]=dp[i−1][j][k]
     * 如果能够开展当前工作 i，设当前小组人数为 group[i]，工作获利为 profit[i]，那么不考虑取模运算的情况下，则有：
     * dp[i][j][k]=dp[i−1][j][k]+dp[i−1][j−group[i]][max(0,k−profit[i])]
     * 由于我们定义的第三维是工作利润至少为 k 而不是 工作利润恰好为 k，因此上述状态转移方程中右侧的第三维是 max(0,k−profit[i]) 而不是 k−profit[i]。
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        final int MODULO = 1000000007;
        int count = 0;
        int len = group.length;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j >= group[i - 1]) {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - group[i - 1]][Math.max(0, k - profit[i - 1])])
                                % MODULO;
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        for (int j = 0; j <= n; j++) {
            count = (count + dp[len][j][minProfit]) % MODULO;
        }
        return count;
    }

    /**
     * 动态规划化简
     * {@link ProfitableSchemes#profitableSchemes1(int, int, int[], int[])}
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        final int MODULO = 1000000007;
        int count = 0;
        int len = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = n; j >= 0; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    if (j >= group[i - 1]) {
                        dp[j][k] = (dp[j][k] + dp[j - group[i - 1]][Math.max(0, k - profit[i - 1])]) % MODULO;
                    } else {
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }
        for (int j = 0; j <= n; j++) {
            count = (count + dp[j][minProfit]) % MODULO;
        }
        return count;
    }
}
