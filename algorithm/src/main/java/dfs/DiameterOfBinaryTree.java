package dfs;

/**
 * @author Croy Qian
 * @createDate 2021/8/5
 * @Description 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * @see <a href="https://leetcode-cn.com/problems/diameter-of-binary-tree/"></a>
 */
public class DiameterOfBinaryTree {

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ldepth = depth(root.left);
        int rdepth = depth(root.right);
        ans = Math.max(ldepth + rdepth, ans);
        //返回深度
        return Math.max(ldepth, rdepth) + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
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
