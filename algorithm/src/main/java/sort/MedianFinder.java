package sort;

import java.util.PriorityQueue;

/**
 * @author Croy Qian
 * @createDate 2021/8/27
 * @Description 数据流中的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */
public class MedianFinder {
    PriorityQueue<Integer> queueMin;
    PriorityQueue<Integer> queueMax;

    public MedianFinder() {
        //小根堆
        queueMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        //大根堆(默认)
        queueMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (queueMin.isEmpty() || num <= queueMin.peek()) {
            queueMin.offer(num);
            if (queueMax.size() + 1 < queueMin.size()) {
                queueMax.offer(queueMin.poll());
            }
        } else {
            queueMax.offer(num);
            if (queueMax.size() > queueMin.size()) {
                queueMin.offer(queueMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queueMin.size() > queueMax.size()) {
            return queueMin.peek();
        }
        return (queueMin.peek() + queueMax.peek()) / 2.0;
    }
}
