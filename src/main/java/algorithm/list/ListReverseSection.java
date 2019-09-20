package algorithm.list;

import utils.ListUtil;

public class ListReverseSection {
    public static void main(String[] args) {
        //1234567 2,6 -> 1654327
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        ListNode newList = reverseSection(head, 1, 2);
        ListUtil.printList(newList);
    }

    //start一定合法，end可能会越界
    static ListNode reverseSection(ListNode head, int start, int end) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        //找到start节点的ore节点
        for (int i = 1; i < start; i++) {
            pre = pre.next;
        }
        ListNode begin = pre.next;
        ListNode newTail = reverseOneSection(pre, end - start);
        System.out.println(newTail);
        return dummy.next;
    }

    //输入[start,end]中start的前驱pre，和end-start的值
    static ListNode reverseOneSection(ListNode pre, int times) {
        ListNode begin = pre.next;
        ListNode tail = begin.next;
        //pre和begin不会动，tail会一个一个动，动times次,times是(start,end]之间的个数
        for (int i = 0; i < times; i++) {
            //如果tail为空就结束
            if (tail == null) break;
            //begin在翻转之后是作为区间里的最后一个，需要指向当前tail的下一个的
            begin.next = tail.next;
            //当前的tail是需要变成头头的，所以指向pre的next
            tail.next = pre.next;
            //现在的头头就是刚上来的tail
            pre.next = tail;
            //tail重新往后走一格
            tail = begin.next;
        }
        return begin;
    }
}
