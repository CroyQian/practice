package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/7/28
 * @Description 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * @see <a href="https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/"></a>
 */
public class AllNodesDistanceKInBinaryTree {
    Map<Integer, TreeNode> parents = new HashMap<>();
    List<Integer> ans = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //构建父节点
        findParentsDfs(root);
        //找距离为k的节点
        findAbs(target, null, 0, k);
        return ans;
    }

    private void findParentsDfs(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            findParentsDfs(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParentsDfs(node.right);
        }
    }

    private void findAbs(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {
            findAbs(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAbs(node.right, node, depth + 1, k);
        }
        if (parents.get(node.val) != from) {
            findAbs(parents.get(node.val), node, depth + 1, k);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
