package simulatedAnnealing;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Croy Qian
 * @createDate 2022/6/1
 * @Description 火柴拼正方形(模拟退火解法)
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * 如果你能使这个正方形，则返回 true ，否则返回 false
 * @tips 模拟退火算法基于概率的算法，从某一较高初温出发，伴随温度参数的不断下降,结合概率突跳特性在解空间中随机寻找目标函数的全局最优解，即在局部最优解能概率性地跳出并最终趋于全局最优。
 * @see <a href="https://leetcode.cn/problems/matchsticks-to-square/"></a>
 */
public class MatchsticksToSquare {
    /**
     * 火柴棒数组
     */
    private int[] matchsticks;
    /**
     * 火柴棒总数
     */
    private int n;
    /**
     * 最后得到的正方形边长
     */
    private int side;

    private Random random = new Random();
    private boolean ans = false;

    /**
     * 退火参数设置
     */
    private double high = 1e4;
    private double low = 1e-4;
    private double fa = 0.98;
    /**
     * 总模拟次数
     */
    int N = 400;

    public boolean makesquare(int[] matchsticks) {
        this.matchsticks = matchsticks;
        this.n = matchsticks.length;
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        this.side = sum / 4;
        shuffle(matchsticks);
        while (N-- > 0) {
            sa();
        }
        return ans;
    }

    /**
     * 打乱数据，避免一开始就进入局部最优
     *
     * @param matchsticks
     */
    private void shuffle(int[] matchsticks) {
        int n = matchsticks.length;
        for (int i = n; i > 0; i--) {
            int index = random.nextInt(i);
            swap(matchsticks, index, i - 1);
        }
    }

    /**
     * 迭代模拟退火
     */
    private void sa() {
        for (double t = high; t > low && !ans; t *= fa) {
            int prev = calc();
            int a = random.nextInt(n), b = random.nextInt(n);
            swap(matchsticks, a, b);
            int cur = calc();
            int diff = prev - cur;
            if (Math.log(diff / t) > random.nextDouble()) {
                swap(matchsticks, a, b);
            }
        }
    }

    /**
     * 计算结果
     *
     * @return
     */
    private int calc() {
        int diff = 0;
        for (int i = 0, j = 0; i < 4; i++) {
            int cnt = 0;
            while (j < n && cnt < side) {
                cnt += matchsticks[j++];
            }
            diff += Math.abs(cnt - side);
        }
        if (diff == 0) {
            ans = true;
        }
        return diff;
    }

    /**
     * 讲数组中 i 和 j 的位置互换
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
