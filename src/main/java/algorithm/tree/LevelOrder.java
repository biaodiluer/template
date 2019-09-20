package algorithm.tree;

import utils.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("1,2,3,4,5,6,7,#,#,#,#,#,#,8,9,#,#,#,#");
        TreeUtil.printTree(root);
        System.out.println(level(root));
        System.out.println(level2(root));
    }

    //非递归，队列
    static List level(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            list.add(cur);
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
        return list;
    }

    //递归，记录层数
    static List level2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root == null) return list;
        int depth = maxDepth(root);//先知道一共有多少层
        for (int i = 1; i <= depth; i++) {
            recursion(root, i, list);
        }
        return list;
    }

    //输出第i层，1是根节点
    static void recursion(TreeNode root, int i, List out) {
        if (root == null || i == 0) return;
        if (i == 1) {
            out.add(root);
            return;
        }
        recursion(root.left, i - 1, out);
        recursion(root.right, i - 1, out);
    }

    //计算最大深度
    static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
