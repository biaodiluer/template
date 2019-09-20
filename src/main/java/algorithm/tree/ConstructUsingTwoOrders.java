package algorithm.tree;

import utils.TreeUtil;

public class ConstructUsingTwoOrders {

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeUtil.printTree(preAndIn(preorder, inorder));
        TreeUtil.printTree(postAndIn(postorder, inorder));
    }

    /*
    通过前序遍历和中序遍历重建树
     */
    static TreeNode preAndIn(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        return preAndInHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode preAndInHelper(int[] preorder, int prestart, int preend,
                                   int[] inorder, int instart, int inend) {
        if (prestart > preend) return null;
        if (prestart == preend) return new TreeNode(preorder[prestart]);
        TreeNode head = new TreeNode(preorder[prestart]);
        //找到根节点在中序里的位置
        int target = 0;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == head.val) {
                target = i;
                break;
            }
        }
        int leftNum = target - instart;
        int rightNum = inend - target;
        head.left = preAndInHelper(preorder, prestart + 1, prestart + leftNum,
                inorder, instart, instart + leftNum - 1);//
        head.right = preAndInHelper(preorder, prestart + leftNum + 1, prestart + leftNum + rightNum,
                inorder, target + 1, target + rightNum);//
        return head;
    }

    /*
    通过后序遍历和中序遍历重建树
     */
    static TreeNode postAndIn(int[] postorder, int[] inorder) {
        if (postorder == null || postorder.length == 0) return null;
        return postAndInHelper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode postAndInHelper(int[] postorder, int poststart, int postend,
                                    int[] inorder, int instart, int inend) {
        if (poststart > postend) return null;
        if (poststart == postend) return new TreeNode(postorder[poststart]);
        TreeNode head = new TreeNode(postorder[postend]);
        //找到根节点在中序里的位置
        int target = 0;
        for (int i = inend; i >= instart; i--) {
            if (inorder[i] == head.val) {
                target = i;
                break;
            }
        }
        int leftNum = target - instart;
        int rightNum = inend - target;
        head.left = postAndInHelper(postorder, poststart, poststart + leftNum - 1,
                inorder, instart, instart + leftNum - 1);//
        head.right = postAndInHelper(postorder, poststart + leftNum, postend - 1,
                inorder, target + 1, target + rightNum);//
        return head;
    }
}
