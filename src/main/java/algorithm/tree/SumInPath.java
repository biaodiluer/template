package algorithm.tree;

import utils.TreeUtil;

public class SumInPath {
    static int max = Integer.MIN_VALUE;

    /*
            ______-2______
         __9__           ___8___
        2     -1      __-3__     3
                     7      4
     */
    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("-2,9,8,2,-1,-3,3,#,#,#,#,7,4,#,#,#,#,#,#");
        maxPathSum1(root, 0);
        System.out.println(max);
        max = Integer.MIN_VALUE;
        maxPathSum5(root);
        System.out.println(max);
        max = Integer.MIN_VALUE;
    }

    /*
    从根节点到任意节点,sum是之前的和
     */
    static public void maxPathSum1(TreeNode root, int sum) {
        //如果是到叶子节点，就在null这里判断
        if (root == null) return;
        int newSum = sum + root.val;
        max = Math.max(max, newSum);
        maxPathSum1(root.left, newSum);
        maxPathSum1(root.right, newSum);
    }

    /*
    任意节点之间路径和最大,可以是先往上再往下，以root为根往下到叶节点路径上的最大和
     */
    static public int maxPathSum5(TreeNode root) {
        if (root == null) return 0;
        //计算左右两边的路径最大和，如果一边的最大和小于0了，就不算内一边
        int l = Math.max(0, maxPathSum5(root.left));
        int r = Math.max(0, maxPathSum5(root.right));
        //把两边的和加上自己
        max = Math.max(max, l + r + root.val);
        //选大的一边加上自己作为return
        return root.val + Math.max(l, r);
    }


}
