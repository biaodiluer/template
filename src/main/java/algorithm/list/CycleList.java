package algorithm.list;

public class CycleList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n4;
        System.out.println(meetNode(head) != null);
        System.out.println(cycleLength(head));
        System.out.println(getEntrance(head));
        //ListNode.printList(head);
    }

    //另外一种是访问过的放入hashset
    static ListNode meetNode(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null) {
            //slow每次走一步，fast每次走两步
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (slow == fast) return slow;
        }
        return null;
    }

    //获得环的长度,从相遇点开始，一个走一步一个走两步，再次相遇时的次数就是环的长度
    static int cycleLength(ListNode head) {
        ListNode meet = meetNode(head);
        if (meet == null) return 0;
        ListNode slow = meet.next, fast = meet.next.next;
        int count = 1;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }
        return count;
    }

    //获得环的入口，一个从相遇点开始，一个从head开始，相遇点就是入口
    static ListNode getEntrance(ListNode head) {
        ListNode firstMeet = meetNode(head);
        ListNode secondMeet = head;
        while (firstMeet != secondMeet) {
            firstMeet = firstMeet.next;
            secondMeet = secondMeet.next;
        }
        return secondMeet;
    }
}
