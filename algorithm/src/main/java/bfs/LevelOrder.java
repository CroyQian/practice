package bfs;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/6/25
 * @Description 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        if (root != null) {
            stack.addLast(root);
        }
        while(!stack.isEmpty()) {
            int currentSize = stack.size();
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = stack.pollFirst();
                level.add(node.val);
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
