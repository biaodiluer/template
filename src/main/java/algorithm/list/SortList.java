package algorithm.list;

import utils.ListUtil;

public class SortList {
    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{-1, 5, 1, 9, 2, 8, 3, 6, 3, 4, 0});
        ListUtil.printList(sortList(head));
    }

    /*
    自顶向下，用递归会有额外空间
     */
    static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode one = head, two = head.next;//这里two要先走一步，不然会爆栈
        while (two != null && two.next != null) {
            one = one.next;
            two = two.next.next;
        }
        ListNode h = one.next;
        one.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(h);
        return MergeSortedList.mergeTwoList(l1, l2);

    }

    /*
    自底向上，用循环，没有额外空间
     */
}
