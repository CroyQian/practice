package slidingWindow;

/**
 * 得到 K 个黑块的最少涂色次数
 * @author Croy Qian
 * @createDate 2023/3/9
 * @Description
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 * @see <a href="https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks"></a>
 */
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {
    private int minimumRecolors(String blocks, int k) {
        /**
         * l - 滑动窗口左边界
         * r - 滑动窗口右边界
         * cur - 滑动窗口有多少W白色块
         */
        int l = 0, r = 0, cnt = 0;
        /**
         * 初始化k大小的滑动窗口
         */
        while (r < k) {
            cnt += blocks.charAt(r) == 'W' ? 1 : 0;
            r++;
        }

        int result = cnt;
        /**
         * 开始滑动窗口
         */
        while (r < blocks.length()) {
            cnt -= blocks.charAt(l) == 'W' ? 1 : 0;
            cnt += blocks.charAt(r) == 'W' ? 1 : 0;
            result = Math.min(result, cnt);
            l++;
            r++;
        }
        return result;
    }
}
