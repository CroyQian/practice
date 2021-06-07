package linkedList;

/**
 * @author Croy Qian
 * @createDate 2021/6/7
 * @Description TODO
 */
public class LinkedListCycle {
    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     *
     * @param head
     * @return
     * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/"></a>
     */
    public Node detectCycle(Node head) {
        Node fast = head;
        Node slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            Node temp = head;
            while (temp != slow) {
                slow = slow.next;
                temp = temp.next;
            }
            return temp;
        } else {
            return null;
        }
    }

    /**
     * 检测环
     *
     * @param list
     * @return
     * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle/submissions/"></a>
     */
    public static boolean checkCircle(Node list) {
        if (list == null) {
            return false;
        }
        Node fast = list;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
