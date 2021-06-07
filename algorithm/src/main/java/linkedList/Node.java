package linkedList;

/**
 * @author Croy Qian
 * @createDate 2021/6/7
 * @Description TODO
 */
public class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
    }
    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
