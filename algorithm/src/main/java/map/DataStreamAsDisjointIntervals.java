package map;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Croy Qian
 * @createDate 2021/10/9
 * @Description 将数据流变为多个不相交区间
 * 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * 实现 SummaryRanges 类：
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 */
public class DataStreamAsDisjointIntervals {
    //可以替换成TreeSet,对应使用
    TreeMap<Integer, Integer> intervals;

    public DataStreamAsDisjointIntervals() {
        intervals = new TreeMap<>();
    }

    public void addNum(int val) {
        // 找到 l1 最小的且满足 l1 > val 的区间 interval1 = [l1, r1]
        // 如果不存在这样的区间，interval1 为尾迭代器
        Map.Entry<Integer, Integer> interval1 = intervals.ceilingEntry(val + 1);
        // 找到 l0 最大的且满足 l0 <= val 的区间 interval0 = [l0, r0]
        // 在有序集合中，interval0 就是 interval1 的前一个区间
        // 如果不存在这样的区间，interval0 为尾迭代器
        Map.Entry<Integer, Integer> interval0 = intervals.floorEntry(val);
        //如果存在一个区间 [l, r]，它完全包含val，即 l≤val≤r，那么在加入val 之后，区间集合不会有任何变化；
        if (interval0 != null && interval0.getKey() <= val && val <= interval0.getValue()) {
            return;
        } else {
            boolean leftAside = interval0 != null && interval0.getValue() + 1 == val;
            boolean rightAside = interval1 != null && interval1.getKey() - 1 == val;
            //如果存在一个区间[l0,r0]满足r0+1=val，并且存在一个区间[l1,r1]满足l1-1=val，则将两个区间合并成一个大区间[l0,r1]
            if (leftAside && rightAside) {
                int left = interval0.getKey(), right = interval1.getValue();
                intervals.remove(interval0.getKey());
                intervals.remove(interval1.getKey());
                intervals.put(left, right);
            } else if (leftAside) {//如果存在一个区间 [l,r]，它的右边界 r「紧贴着」val，即 r + 1 = val，那么在加入 val 之后，该区间会从 [l, r] 变为 [l, r+1]；
                intervals.put(interval0.getKey(), interval0.getValue() + 1);
            } else if (rightAside) {
                int right = interval1.getValue();
                intervals.remove(interval1.getKey());
                intervals.put(val, right);
            } else {
                intervals.put(val, val);
            }
        }
    }

    public int[][] getIntervals() {
        int size = intervals.size();
        int[][] ans = new int[size][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            int left = entry.getKey(), right = entry.getValue();
            ans[index][0] = left;
            ans[index][1] = right;
            index++;
        }
        return ans;
    }
}
