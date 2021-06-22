package backTrace;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/6/22
 * @Description 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * @see <a herf="https://leetcode-cn.com/problems/permutations/"></a>
 *
 * <p>
 * 进阶问题：
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * </p>
 */
public class Permute {
    boolean[] visit;
    List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        visit = new boolean[len];
        result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        /**
         * {@link Stack}
         * 定义了栈的推荐的初始化写法
         */
        //        Deque<Integer> path = new ArrayDeque<Integer>();
        backTrace(nums, 0, len, tempList);
        //        backTraceStack(nums, 0, len, path);
        return result;
    }

    /**
     * @param nums
     * @param i
     * @param len
     * @param tempList
     */
    private void backTrace(int[] nums, int i, int len, List<Integer> tempList) {
        if (i == len) {
            //因为java里传的是引用，这里要使用拷贝，否则tempList在不断回溯中最后为空，result的值也变成了空
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int j = 0; j < len; j++) {
            if (!visit[j]) {
                tempList.add(nums[j]);
                visit[j] = true;
                backTrace(nums, i + 1, len, tempList);
                tempList.remove(tempList.size() - 1);
                visit[j] = false;
            }
        }
    }

    /**
     * 用栈也可以，但相比用list要慢一点，因为构建stack花的时间比较长
     *
     * @param nums
     * @param i
     * @param len
     * @param path {@link Stack}
     */
    private void backTraceStack(int[] nums, int i, int len, Deque<Integer> path) {
        if (i == len) {
            //因为java里传的是引用，这里要使用拷贝，否则tempList在不断回溯中最后为空，result的值也变成了空
            result.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < len; j++) {
            if (!visit[j]) {
                path.addLast(nums[j]);
                visit[j] = true;
                backTraceStack(nums, i + 1, len, path);
                path.removeLast();
                visit[j] = false;
            }
        }
    }

    /**
     * 也可以使用set去重，参考Permutation
     * {@link Permutation}
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        visit = new boolean[len];
        result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(nums);
        backTraceEx(nums, 0, len, tempList);
        return result;
    }

    private void backTraceEx(int[] nums, int i, int len, List<Integer> tempList) {
        if (i == len) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int j = 0; j < len; j++) {
            if (visit[j] || (j > 0 && !visit[j-1] && nums[j-1] == nums[j])) {
                continue;
            }
            visit[j] = true;
            tempList.add(nums[j]);
            backTraceEx(nums, i+1, len, tempList);
            tempList.remove(tempList.size() - 1);
            visit[j] = false;
        }
    }
}
