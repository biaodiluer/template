package algorithm.tree;

import utils.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrder {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("4,2,6,1,3,5,7,#,#,#,#,#,#,#,#");
        TreeUtil.printTree(root);
        List list = recursion(root);
        System.out.println(list);
    }

    static List recursion(TreeNode root) {
        List<TreeNode> list = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode father = stack.pop();
                list.add(father);
                cur = father.right;
            }
        }
        return list;
    }

    static void nonRecursion(TreeNode root) {
        if (root == null) return;
        nonRecursion(root.left);
        System.out.print(root.val + " ");
        nonRecursion(root.right);
    }
}
