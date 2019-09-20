package utils;

import algorithm.list.ListNode;

public class ListUtil {
    public static ListNode initList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode pre = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode curNode = new ListNode(arr[i]);
            pre.next = curNode;
            pre = pre.next;
        }
        return head;
    }

    public static void printList(ListNode head) {
        String ans = "";
        while (head != null) {
            ans += head.val + "->";
            head = head.next;
        }
        ans += "null";
        System.out.println(ans);
    }
}
