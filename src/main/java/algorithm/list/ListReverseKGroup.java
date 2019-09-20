package algorithm.list;

import utils.ListUtil;

import java.util.LinkedList;
import java.util.List;

public class ListReverseKGroup {
    //每k个翻转1-2-3-4-5-6-7 k=3=> 3-2-1-6-5-4-7
    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        ListNode newList = reverseK1(head, 5);
        ListUtil.printList(newList);
    }

    //不用额外空间
    static ListNode reverseK1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = 0;
        while (pre.next != null) {
            if (count % k == 0) {
                //这里是pre的位置
                ListNode begin = pre.next;
                ListReverseSection.reverseOneSection(pre, k - 1);
                pre = begin;
                count += k;
            } else {
                pre = pre.next;
                count++;
            }
            //ListNode.printList(dummy.next);
        }
        return dummy.next;
    }

    //需要用额外空间
    static ListNode reverseK2(ListNode head, int k) {
        //1、先将没每k个的头结点保存起来，并且将尾巴断掉
        List<ListNode> heads = new LinkedList();
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            //子节点有可能会断掉，所以要先保存一下
            ListNode nextNode = cur.next;
            if (count % k == 1) {
                //子链表的头结点
                heads.add(cur);
            } else if (count % k == 0) {
                //子链表的尾节点
                cur.next = null;
            }
            cur = nextNode;
        }
        ListNode dummy = new ListNode(0);
        cur = dummy;
        for (ListNode subHead : heads) {
            ListNode newSubList = ListReverse.recursion(subHead);
            cur.next = newSubList;
            cur = subHead;
        }
        return dummy.next;
    }
}
