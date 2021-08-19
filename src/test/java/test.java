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
        ListNode nodes=t1.removeNthFromEnd(head, 5);
        showListNode(nodes);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode removeNode=head;
        int length=0;
        while (removeNode!=null)
        {
            length++;
            removeNode=removeNode.next;
        }
        int removeIndex=length-(n-1);
        System.out.println(String.format("要删除的是第%S个",removeIndex));
        if(removeIndex==1) return head.next;
        removeNode=head;

        int k=0;
        while (removeNode!=null){
            k++;
            //确定是要删除的node
            if(k==(removeIndex-1)){
                removeNode.next=removeNode.next.next;
                break;
            }
            removeNode=removeNode.next;
        }
        return head;
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