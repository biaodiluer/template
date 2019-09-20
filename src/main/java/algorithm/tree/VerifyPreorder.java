package algorithm.tree;

import java.util.Stack;

public class VerifyPreorder {
    /*
    给定前序表达的序列，判断是否合法，空节点用#表示
     */
    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String preorder1 = "7,2,#,2,#,#,#,6,#";
        System.out.println(isValidSerialization2(preorder));
    }

    /*
    方法一，使用栈，把访问过的子树变成#，如果是数字直接入栈，如果是#，并且栈顶也为#，则同时弹出两个元素，再把#入栈
    最后栈里应该只剩下一个#才对
    时间O(2n) 空间O(n)
     */
    static boolean isValidSerialization1(String preorder) {
        if (preorder == null || preorder.length() == 0) return false;
        String[] arr = preorder.split(",");
        Stack<String> stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            String cur = arr[i];
            while (!stack.empty() && cur.equals("#") && cur.equals(stack.peek())) {
                stack.pop();
                if (stack.empty()) return false;
                stack.pop();
            }
            stack.push(cur);
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }

    /*
    方法2，最后结果非#数量+1=#数量，并且在过程中，非#数量>=数量
     */
    static boolean isValidSerialization2(String preorder) {
        int leave = 0, notLeave = 0;
        String[] arr = preorder.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("#")) {
                leave++;
            } else {
                notLeave++;
            }
            if (leave == notLeave + 1 && i != arr.length - 1) return false;
        }
        return leave == notLeave + 1;
    }

}
