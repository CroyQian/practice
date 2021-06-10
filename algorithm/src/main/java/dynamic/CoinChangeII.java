package dynamic;

/**
 * @author Croy Qian
 * @createDate 2021/6/10
 * @Description 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个
 */
public class CoinChangeII {
    /**
     * 想复杂了，用dp[i][j]表示i种硬币达到金额j的方案数
     * @param amount
     * @param coins
     * @return
     */
    public int change1(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i -1][j] + dp[i][j - coins[i-1]];
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= len; i++) {
            sum += dp[i][amount];
        }
        return sum;
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
