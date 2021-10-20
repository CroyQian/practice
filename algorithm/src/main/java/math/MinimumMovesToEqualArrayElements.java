package math;

import java.util.Arrays;

/**
 * @author Croy Qian
 * @createDate 2021/10/20
 * @Description 最小操作次数使数组元素相等
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * @see <a href="https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/"></a>
 * 思路：n-1个元素加1相当于一个元素减1
 */
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        int count = 0;
        int min = Arrays.stream(nums).min().getAsInt();
        for (int num : nums) {
            count += num - min;
        }
        return count;
    }
}
