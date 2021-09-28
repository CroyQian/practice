package dfs;

import java.util.HashMap;

/**
 * @author Croy Qian
 * @createDate 2021/9/28
 * @Description 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        return getPath(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public int getPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        count += getPath(root.left, sum - root.val) + getPath(root.right, sum - root.val);
        return count;
    }

    public int pathSumEx(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode root, HashMap<Integer, Integer> prefix, int curr, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        //回溯
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
