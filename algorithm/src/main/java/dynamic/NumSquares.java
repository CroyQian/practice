package dynamic;

/**
 * @author Croy Qian
 * @createDate 2021/6/11
 * @Description 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class NumSquares {
    /**
     * 动态规划
     * f[i] 表示最少需要多少个数的平方来表示整数 i
     * 这些数必然落在区间 [1,sqrt{n}]。我们可以枚举这些数，假设当前枚举到 j，那么我们还需要取若干数的平方，构成 i-j^2。
     * 此时我们发现该子问题和原问题类似，只是规模变小了。这符合了动态规划的要求，于是我们可以写出状态转移方程。
     *
     * @param n
     * @return
     */
    public int numSquares1(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minVal = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minVal = Math.min(minVal, dp[i - j * j]);
            }
            dp[i] = minVal + 1;
        }
        return dp[n];
    }

    /**
     * 四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界。
     * 同时四平方和定理包含了一个更强的结论：当且仅当 n !=4^k×(8m+7) 时，n 可以被表示为至多三个正整数的平方和。
     * 4^k×(8m+7) 时，n 只能被表示为四个正整数的平方和。此时我们可以直接返回 4。
     * 当 n !=4^k×(8m+7) 时，我们需要判断到底多少个完全平方数能够表示 n，我们知道答案只会是 1,2,3 中的一个：
     * 答案为 1 时，则必有 n 为完全平方数，这很好判断；
     * 答案为 2 时，则有 n=a^2+b^2,我们只需要枚举所有的是否为完全平方数即可；
     * 答案为 3 时，我们很难在一个优秀的时间复杂度内解决它，但我们只需要检查答案为 1 或 2 的两种情况，即可利用排除法确定答案。
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }

}
