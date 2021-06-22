package backTrace;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/6/22
 * @Description 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * @see <a href="https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/"></a>
 */
public class Permutation {
    List<String> rec;
    boolean[] vis;
    Set<String> set = new HashSet<>();

    /**
     * 方法一：我们将这个问题看作有 nn 个排列成一行的空位，我们需要从左往右依次填入题目给定的 n 个字符，每个字符只能使用一次。
     * 首先可以想到穷举的算法，即从左往右每一个空位都依次尝试填入一个字符，看是否能填完这 n 个空位，编程实现时，我们可以用「回溯法」来模拟这个过程。
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    /**
     * 方法一：定义递归函数 backtrack(i,perm) 表示当前排列为 perm，下一个待填入的空位是第 i 个空位（下标从 0 开始）。
     * 那么该递归函数分为两个情况：
     * <p>
     * 如果i=n，说明我们已经填完了 n 个空位，找到了一个可行的解，我们将 perm 放入答案数组中，递归结束。
     * <p>
     * 如果i<n，此时需要考虑第 i 个空位填哪个字符。根据题目要求我们肯定不能填已经填过的字符，
     * 因此很容易想到的一个处理手段是我们定义一个标记数组 vis 来标记已经填过的字符，
     * 那么在填第 i 个字符的时候我们遍历题目给定的 n 个字符，如果这个字符没有被标记过，
     * 我们就尝试填入，并将其标记，继续尝试填下一个空位，即调用函数 backtrack(i+1,perm)。
     * 回溯时，我们需要要撤销该空位填的字符以及对该字符的标记，并继续向当前空位尝试填入其他没被标记过的字符。
     *
     * @param arr
     * @param i
     * @param n
     * @param perm
     */
    private void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            /**
             * 设定一个规则，保证在填每一个空位的时候重复字符只会被填入一次。
             * 具体地，我们首先对原字符串排序，保证相同的字符都相邻，
             * 在递归函数中，我们限制每次填入的字符一定是这个字符所在重复字符集合中「从左往右第一个未被填入的字符」
             */
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    /**
     * 方法二：
     * 直接使用set来去重
     *
     * @param s
     * @return
     */
    public String[] permutationEx(String s) {
        int n = s.length();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        StringBuffer perm = new StringBuffer();
        backtrackEx(arr, 0, n, perm);
        int size = set.size();
        String[] recArr = new String[size];
        Iterator<String> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            recArr[i] = iterator.next();
            i++;
        }
        return recArr;
    }

    /**
     * 方法二：
     * 直接使用set来去重
     *
     * @param arr
     * @param i
     * @param n
     * @param perm
     */
    private void backtrackEx(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            set.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!vis[j]) {
                vis[j] = true;
                perm.append(arr[j]);
                backtrackEx(arr, i + 1, n, perm);
                perm.deleteCharAt(perm.length() - 1);
                vis[j] = false;
            }
        }
    }
}
