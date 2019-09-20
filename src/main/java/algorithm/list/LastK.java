package algorithm.list;

import utils.ListUtil;

public class LastK {
    public static void main(String[] args) {
        ListNode head = ListUtil.initList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        ListNode node = lastK(head, 10);
        System.out.println(node);
    }

    private static ListNode lastK(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        //先让fast走K步
        while (fast != null && k-- > 0) {
            fast = fast.next;
        }
        if (fast == null) return null;
        //再让fast走到尾巴后面的null
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
