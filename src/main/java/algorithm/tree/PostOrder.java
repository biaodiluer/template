package algorithm.tree;

import utils.TreeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostOrder {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("7,3,6,1,2,4,5,#,#,#,#,#,#,#,#");
        TreeUtil.printTree(root);
        //nonRecursion(root);
        List list = recursion(root);
        System.out.println(list);
    }

    static List recursion(TreeNode root) {
        List<TreeNode> list = new LinkedList();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        //后续遍历倒过来就是前序遍历的对称版本，父-右-左
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                list.add(0, cur);
                stack.push(cur);
                cur = cur.right;
            } else {
                TreeNode father = stack.pop();
                cur = father.left;
            }
        }
        return list;
    }

    static void nonRecursion(TreeNode root) {
        if (root == null) return;
        nonRecursion(root.left);
        nonRecursion(root.right);
        System.out.print(root.val + " ");
    }
}
