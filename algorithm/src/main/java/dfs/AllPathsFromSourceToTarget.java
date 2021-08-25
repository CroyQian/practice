package dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Croy Qian
 * @createDate 2021/8/25
 * @Description 所有可能的路径
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
 * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
 * @see <a href="https://leetcode-cn.com/problems/all-paths-from-source-to-target"></a>
 */
public class AllPathsFromSourceToTarget {
    private Deque<Integer> stack = new ArrayDeque<>();
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return result;
    }

    private void dfs(int[][] graph, int from, int to) {
        if (from == to) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int temp : graph[from]) {
            stack.offerLast(temp);
            dfs(graph, temp, to);
            stack.pollLast();
        }
    }
}
