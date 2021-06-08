package dynamic;

/**
 * @author Croy Qian
 * @createDate 2021/6/8
 * @Description 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * @see <a href="https://leetcode-cn.com/problems/last-stone-weight-ii/"></a>
 */
public class LastStoneWeight {
    /**
     * 动态规划
     * 记这两堆石头重量之差的绝对值为 diff。若能找到一种粉碎方案，使得最后一块石头的重量也为 diff，那就能说明这组 {ki} 是合法的。
     * 我们不断地粉碎石头。每次粉碎时，记重量最大的石头所处的堆为 A（若两堆最大重量相同则任选一堆），另一堆为 B。从 A 中取出重量最大的石头，B 中任取一石头，若没有完全粉碎，则将新石头重新放入 A。这一操作从每堆石头中减去了同样的重量，从而保证重量之差的绝对值在粉碎前后是不变的。
     * 若出现一堆没有石头，而另一堆不止一块石头的情况，记有石头的那一堆为 AA，另一堆为 BB。要继续粉碎，则需要从 AA 中取出一块石头移入 BB，然后按规则粉碎。但移入操作让重量之差的绝对值变得更小，与事实（上文加粗文字）矛盾，所以不会出现这种情况。
     * 因此，按照上述流程操作，最后一块石头的重量为 diff，所以这组 ki 对应着一个合法的粉碎结果。
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII1(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int n = stones.length;
        int m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= stones[i]) {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                } else {
                    dp[i + 1][j] = dp[i][j];
                }
            }
        }
        for (int j = m; j >= 0; j--) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
        return -1;
    }

    /**
     * dp 的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 dp 的第一个维度，将空间复杂度优化到 O(neg)。
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int n = stones.length;
        int m = sum / 2;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int stone : stones) {
            for (int j = m; j >= 0; j--) {
                if (j >= stone) {
                    dp[j] = dp[j] || dp[j - stone];
                }
            }
        }
        for (int j = m; j >= 0; j--) {
            if (dp[j]) {
                return sum - 2 * j;
            }
        }
        return -1;
    }
}
