package linkedList;

/**
 * @author Croy Qian
 * @createDate 2021/9/22
 * @Description 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * 返回一个由上述 k 部分组成的数组。
 */
public class SplitLinkedListInParts {
    public Node[] splitListToParts(Node head, int k) {
        int len = 0;
        Node temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        int quotient = len/k, remainder = len % k;

        Node[] parts = new Node[k];
        Node curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j ++) {
                curr = curr.next;
            }
            Node next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }
}
