package map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/6/24
 * @Description 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class MaxPoints {
    public int maxPoints(int[][] points) {
        int n = points.length;
        //在点的总数量小于等于 22 的情况下，我们总可以用一条直线将所有点串联
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            /**
             * 当我们找到一条直线经过了图中超过半数的点时，我们即可以确定该直线即为经过最多点的直线
             * 当我们枚举到点 i（假设编号从 0 开始）时，我们至多只能找到 n−i 个点共线。假设此前找到的共线的点的数量的最大值为 k，如果有 k≥n−i，那么此时我们即可停止枚举，因为不可能再找到更大的答案了。
             */
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i+1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    /**
     * 求最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
