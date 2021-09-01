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
    static ListNode head2 = new ListNode(1);

    public static void main(String[] args) {


        head.next = new ListNode(2);
        head.next.next = new ListNode(4);

        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);


        showListNode(head);
        showListNode(head2);

        test t1 = new test();
        ListNode listNode = t1.mergeTwoLists(head, head2);
        showListNode(listNode);


    }

    /*
     * @Author Neal
     * @Description //
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     * @Date 9:33 2021/9/1
     * @Param
     * @return
     **/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode nodes=new ListNode(0);
        ListNode head=nodes;
        while (l1!=null||l2!=null)
        {
            if(l1==null) {
                nodes.next=l2;
                break;
            }
            if(l2==null){
                nodes.next=l1;
                break;
            }
            if(l1.val<=l2.val){
                nodes.next=l1;
                l1=l1.next;
                nodes=nodes.next;
            }else
            {
                nodes.next=l2;
                l2=l2.next;
                nodes=nodes.next;
            }
        }

        return head.next;
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

            if (head.next == null) {
                break;
            }
        }


        return prev;

    }



    public static void showListNode(ListNode nodes) {

        if (nodes==null) return;
        ListNode nodefalg = nodes;
        while (true) {
            System.out.print(nodefalg.val+"-->");
            if (nodefalg.next == null) break;
            nodefalg = nodefalg.next;
        }
        System.out.println();
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