package utils;

import algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtil {
    public static TreeNode initTree(String input) {
        if (input == null || input.length() == 0) return null;
        String[] arr = input.split(",");
        Queue<TreeNode> q = new LinkedList();
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(arr[i++]));
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            TreeNode left = null, right = null;
            if (!arr[i].equals("#")) {
                left = new TreeNode(Integer.parseInt(arr[i]));
                cur.left = left;
                q.offer(left);
            }
            i++;
            if (!arr[i].equals("#")) {
                right = new TreeNode(Integer.parseInt(arr[i]));
                cur.right = right;
                q.offer(right);
            }
            i++;
        }
        return root;
    }

    public static void printTree(TreeNode root) {
        String ans = "";
        if (root == null) {
            ans += "null";
        } else {
            Queue<TreeNode> q = new LinkedList();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur != null) {
                    ans += cur.val + ",";
                    q.offer(cur.left);
                    q.offer(cur.right);
                } else {
                    ans += "null,";
                }
            }
        }
        System.out.println(ans.substring(0, ans.length() - 1));
    }
}
