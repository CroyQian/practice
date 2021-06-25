package dfs;

import java.util.Arrays;

/**
 * @author Croy Qian
 * @createDate 2021/6/23
 * @Description 给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。同时给你 start，finish 和 fuel 分别表示出发城市、目的地城市和你初始拥有的汽油总量
 * 每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足  j != i 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到 j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。
 * 请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。
 * 请你返回从 start 到 finish 所有可能路径的数目。
 * 由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
 */
public class CountRoutes {
    int mod = 1000000007;

    // 缓存器：用于记录「特定状态」下的结果
    // cache[i][fuel] 代表从位置 i 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
    int[][] cache;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;

        // 初始化缓存器
        // 之所以要初始化为 -1
        // 是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
        cache = new int[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
//        return dfs(locations, start, finish, fuel);
        return dfsEx(locations, start, finish, fuel);
    }

    private int dfs(int[] locations, int start, int end, int fuel) {
        // 如果缓存器中已经有答案，直接返回
        if (cache[start][fuel] != -1) {
            return cache[start][fuel];
        }

        int n = locations.length;
        // base case 1：如果油量为 0，且不在目标位置
        // 将结果 0 写入缓存器并返回
        if (fuel == 0 && start != end) {
            cache[start][fuel] = 0;
            return 0;
        }

        // base case 2：油量不为 0，且无法到达任何位置
        // 将结果 0 写入缓存器并返回
        boolean hasNext = false;
        for (int i = 0; i < n; i++) {
            if (i != start) {
                int need = Math.abs(locations[start] - locations[i]);
                if (fuel >= need) {
                    hasNext = true;
                    break;
                }
            }
        }
        if (fuel != 0 && !hasNext) {
            int a= cache[start][fuel] = start==end?1:0;
            return a;
        }

        // 计算油量为 fuel，从位置 u 到 end 的路径数量
        // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径
        int sum = start == end ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != start) {
                int need = Math.abs(locations[i] - locations[start]);
                if (fuel >= need) {
                    sum += dfs(locations, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[start][fuel] = sum;
        return sum;
    }

    /**
     * 「无效情况」的 Base Case 是可以进一步简化的。
     *
     * 考虑一个问题：如果我们从某个位置 start出发，不能一步到达目标位置的话，有可能使用多步到达目标位置吗？
     * 也就是一步不行的话，多步可以吗？
     * 答案是不可以。
     *
     * 假设我们当前位置a，目标位置b，两者差值的绝对值为 need，而当前油量是 fuel。
     * 不能一步到达，说明 fuel > need。
     * 而我们每次移动到新的位置，消耗的油量 cost都是两个位置的差值绝对值。
     * 正因为 cost >= 0，因此我们移动到新位置后的油量 fuel' <= fuel。
     * 换句话说，即使从新位置b出发到目标位置，也无法到达终点。
     *
     * 如果我们在某个位置start出发，不能一步到达目的地end，将永远无法到达目的地。
     * @param ls
     * @param u
     * @param end
     * @param fuel
     * @return
     */
    int dfsEx(int[] ls, int u, int end, int fuel) {
        // 如果缓存中已经有答案，直接返回
        if (cache[u][fuel] != -1) {
            return cache[u][fuel];
        }

        // 如果一步到达不了，说明从位置 u 不能到达 end 位置
        // 将结果 0 写入缓存器并返回
        int need = Math.abs(ls[u] - ls[end]);
        if (need > fuel) {
            cache[u][fuel] = 0;
            return 0;
        }

        int n = ls.length;
        // 计算油量为 fuel，从位置 u 到 end 的路径数量
        // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径
        int sum = u == end ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != u) {
                need = Math.abs(ls[i] - ls[u]);
                if (fuel >= need) {
                    sum += dfs(ls, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[u][fuel] = sum;
        return sum;
    }
}
