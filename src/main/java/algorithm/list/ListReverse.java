package algorithm.list;

import utils.ListUtil;

public class ListReverse {
    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5, 6});
//        System.out.println("before: ");
//        ListNode.printList(head);
//
//        ListNode newHead=nonRecursion(head);
//        System.out.println("after: ");
//        ListNode.printList(newHead);
    }

    //普通翻转递归
    static ListNode recursion(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode newHead = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //普通翻转迭代
    static ListNode nonRecursion(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;//必须先获得下一个节点
            cur.next = pre;//下一个节点有引用了，就让当前指向前面的
            pre = cur;//更新前面的
            cur = next;//更新当前的
        }
        return pre;
    }
}
