package dynamic;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/5/13
 * @Description TODO
 */
public class Dynamic {
    /**
     * 最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     * @see <a href="https://leetcode-cn.com/problems/longest-common-subsequence/"></a>
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        //从text1的[0..i]到text2的[0..j]的最大子序列
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 停在原地的方案数
     *
     * @param steps
     * @param arrLen
     * @return
     * @see <a href="https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/"></a>
     */
    public int numWays(int steps, int arrLen) {
        final int MODULO = 1000000007;
        int maxColumn = Math.min(arrLen - 1, steps);
        //通过i步到j
        int[][] dp = new int[steps + 1][maxColumn + 1];
        //0步即什么都不做，原地不动 可得 dp[0][1..j] = 0(默认值)
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxColumn; j++) {
                //不动
                dp[i][j] = dp[i - 1][j];
                //往前一步 + 1 ， 上一步不可能在-1处
                if (j - 1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                }
                //回走一步 -1，上一步不可能在maxColumn
                if (j + 1 <= maxColumn) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return dp[steps][0];
    }

    /**
     * 最长回文串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示从i到j是回文字符串
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //L 为子串长度
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) {
                    break;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
