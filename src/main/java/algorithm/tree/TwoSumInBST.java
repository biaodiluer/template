package algorithm.tree;

import utils.TreeUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoSumInBST {
    /*
    方法2：一边遍历，一边找另一半是否在hash中，和BST无关，时间On空间On
     */
    static Set<Integer> set = new HashSet();

    public static void main(String[] args) {
        TreeNode root = TreeUtil.initTree("5,3,6,2,4,#,7,#,#,#,#,#,#");
        System.out.println(findTarget3(root, 14));
    }

    /*
    方法一，中序遍历放入数组，左右两个指针移动，时间ON 空间ON
     */
    static public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        List<TreeNode> list = new ArrayList();
        inorder(root, list);
        int size = list.size();
        int l = 0, r = size - 1;
        while (l < r) {
            int sum = list.get(l).val + list.get(r).val;
            if (sum == k) return true;
            if (sum < k) l++;
            else r--;
        }
        return false;
    }

    static void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }

    static public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget2(root.left, k) || findTarget2(root.right, k);
    }

    /*
    方法3：情况1，在整颗树中搜索k-val（不包括自己）
    情况23，在整颗树中搜索左节点和右节点
     */
    static public boolean findTarget3(TreeNode root, int k) {
        if (root == null) return false;
        return dfs(root, root, k);
    }

    static boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null) return false;
        //树中是否存在k-当前节点.val || 当前点变成左节点 || 当前点变成右节点
        return exist(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    static boolean exist(TreeNode root, TreeNode cur, int target) {
        //在整颗数中找target，并且target！=cur
        if (root == null) return false;
        TreeNode temp = root;
        while (temp != null) {
            if (temp.val == target) {
                if (temp != cur) return true;
                else return false;
            }
            if (temp.val < target) temp = temp.right;
            else temp = temp.left;
        }
        return false;
    }
}
