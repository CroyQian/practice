package linkedList;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/4/8
 * @Description TODO
 */
public class LinkedListAlgorithm {
    /**
     * 反转链表
     *
     * @param list
     * @return
     * @see <a href="https://leetcode-cn.com/problems/reverse-linked-list/"></a>
     */
    public static Node reverse(Node list) {
        Node curr = list, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     * @see <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/"></a>
     */
    public static Node mergeTwoSortNode(Node l1, Node l2) {
        Node soldier = new Node(0, null);
        Node p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return soldier.next;
    }

    /**
     * 链表的中间结点
     *
     * @param list
     * @return
     * @see <a href="https://leetcode-cn.com/problems/middle-of-the-linked-list/"></a>
     */
    public static Node findMiddleNode(Node list) {
        if (list == null) {
            return null;
        }
        Node fast = list;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 合并K个有序链表(递归合并两个的情况)
     *
     * @param lists
     * @return
     * @see <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/"></a>
     */
    public static Node mergeKSortNode(Node[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTwoSortNode(lists[0], lists[1]);
        }
        int mid = lists.length / 2;
        Node[] first = new Node[mid];
        for (int i = 0; i < mid; i++) {
            first[i] = lists[i];
        }
        Node[] second = new Node[lists.length - mid];
        for (int i = mid, j = 0; i < lists.length; i++, j++) {
            second[j] = lists[i];
        }
        return mergeTwoSortNode(mergeKSortNode(first), mergeKSortNode(second));
    }



}
