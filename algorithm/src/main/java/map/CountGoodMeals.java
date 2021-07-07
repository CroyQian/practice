package map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/7/7
 * @Description 大餐计数
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * @see <a href="https://leetcode-cn.com/problems/count-good-meals/"></a>
 */
public class CountGoodMeals {
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int max = 0;
        for (int val : deliciousness) {
            max = Math.max(val, max);
        }
        max = max * 2;
        int countSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i < deliciousness.length; i++) {
            int curr = deliciousness[i];
            for (int sum = 1; sum <= max; sum <<= 1) {
                int count = map.getOrDefault(sum - curr, 0);
                countSum = (countSum + count) % MOD;
            }
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        return countSum;
    }

}
