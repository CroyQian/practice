package bfs;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/7/1
 * @Description 传递信息
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 *
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * @see <a href="https://leetcode-cn.com/problems/chuan-di-xin-xi"></a>
 */
public class DeliveringInformation {
    public int numWays(int n, int[][] relation, int k) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : relation) {
            edges.get(edge[0]).add(edge[1]);
        }
        int step = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while (!queue.isEmpty() && step < k) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                List<Integer> list = edges.get(curr);
                for (int next : list) {
                    queue.offer(next);
                }
            }
        }
        int ways = 0;
        if (step == k) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n-1) {
                    ways++;
                }
            }
        }
        return ways;
    }
}
