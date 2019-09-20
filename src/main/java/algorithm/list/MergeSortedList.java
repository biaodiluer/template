package algorithm.list;

import utils.ListUtil;

public class MergeSortedList {
    public static void main(String[] args) {
        ListNode head1 = ListUtil.initList(new int[]{1, 2, 3, 5, 7, 9});
        ListNode head2 = ListUtil.initList(new int[]{2, 4, 5, 6, 8, 10, 11, 12});
        ListNode head3 = ListUtil.initList(new int[]{10, 11, 12, 12, 13, 14, 15});
        ListNode head4 = ListUtil.initList(new int[]{-1, 1, 3, 5, 7, 9});
        ListNode[] lists = new ListNode[]{head1, head2, head3, head4};
        ListNode newHead = mergeKList(lists);
        ListUtil.printList(newHead);
    }

    /*
    合并两个list
     */
    static ListNode mergeTwoList(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        if (head1.val < head2.val) {
            head1.next = mergeTwoList(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeTwoList(head1, head2.next);
            return head2;
        }
    }

    /*
    合并K个list
     */
    static ListNode mergeKList(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    private static ListNode partion(ListNode[] lists, int left, int right) {
        if (left > right) return null;
        if (left == right) return lists[left];
        int mid = (left + right) >> 1;
        ListNode l1 = partion(lists, left, mid);
        ListNode l2 = partion(lists, mid + 1, right);
        return mergeTwoList(l1, l2);
    }

}
