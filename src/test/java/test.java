import org.w3c.dom.ls.LSException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName test
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/12 9:41
 * @Version 1.0
 **/
public class test {
    static ListNode head = new ListNode(1);

    public static void main(String[] args) {


        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        showListNode(head);

        test t1 = new test();
        ListNode nodes = t1.reverseList(head);
        showListNode(nodes);
    }

    public ListNode reverseList(ListNode head) {

        if (head == null) return null;
        if (head.next == null) return head;

        ListNode prev = head;
        ListNode next = head.next;
        while (true) {

            //1 找出当前的头和下一个
            //2. 交换头和下一个的执行
            ListNode old_next = next.next;
            head.next = old_next;
            next.next = prev;
            prev = next;
            next = head.next;

            if(head.next==null){
                break;
            }
        }


        return prev;

    }

    public static void showListNode(ListNode nodes) {
        ListNode nodefalg = nodes;
        while (true) {
            System.out.println(nodefalg.val);
            if (nodefalg.next == null) break;
            nodefalg = nodefalg.next;
        }
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}