package algorithm.tree;

import utils.TreeUtil;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("3,5,1,6,2,0,8,#,#,7,4,#,#,#,#,#,#,#,#");
        TreeNode p = root.left;
        TreeNode q = root.left.right.right;
        TreeUtil.printTree(root);
        System.out.println(lowestCommonAncestor1(root, p, q));
        System.out.println(lowestCommonAncestor2(root, p, q));
    }

    /*
    方法1：递归，如果为空返回空，如果有一个值和root相同，姑且认为root就是最小祖先
    否则，就各在左右子树找，如果左右子树都有祖先，说明pq在两边，如果只有一边找到了，说明就是内一边
    如果都没有，那就没有祖先节点
     */
    static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;
        //要么都在左边，要么都在右边，要么一左一右
        TreeNode la = lowestCommonAncestor1(root.left, p, q);
        TreeNode ra = lowestCommonAncestor1(root.right, p, q);
        TreeNode ancestor = null;
        if (la != null && ra != null) {
            ancestor = root;
        } else if (la != null && ra == null) {
            //两个节点的祖先在左边
            ancestor = la;
        } else if (la == null && ra != null) {
            //两个节点的祖先在左边
            ancestor = ra;
        }
        return ancestor;
    }

    /*
    方法2：记录root到pq的路径，再从头开始找最后一个一样的
     */
    static public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathp = new ArrayList();
        List<TreeNode> pathq = new ArrayList();
        List<TreeNode> temp = new ArrayList();
        dfs(root, p, pathp, temp);
        dfs(root, q, pathq, temp);
        int i = 0;
        int j = 0;
        while (i < pathp.size() && j < pathq.size() && pathp.get(i) == pathq.get(j)) {
            i++;
            j++;
        }
        return pathp.get(Math.max(i, j) - 1);
    }

    static void dfs(TreeNode root, TreeNode node, List<TreeNode> path, List<TreeNode> temp) {
        if (root == null) return;
        temp.add(root);
        if (root == node) {
            for (TreeNode t : temp) {
                path.add(t);
            }
            temp.remove(temp.size() - 1);
            return;
        }
        dfs(root.left, node, path, temp);
        dfs(root.right, node, path, temp);
        temp.remove(temp.size() - 1);
    }
}
