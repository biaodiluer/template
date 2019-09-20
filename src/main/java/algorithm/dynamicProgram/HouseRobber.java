package algorithm.dynamicProgram;

import algorithm.tree.TreeNode;
import utils.TreeUtil;

public class HouseRobber {


    public static void main(String[] args) {
        int[] arr1=new int[]{1,2,3,1};
        int[] arr2=new int[]{2,3,2};
        TreeNode root= TreeUtil.initTree("3,2,3,#,3,#,1,#,#,#,#");
        System.out.println(rob1(arr1));
        System.out.println(rob2(arr2));
        System.out.println(rob3(root));
    }

    /*
    场景1
    数组元素是钱的数量，相邻偷会报警，求最多能偷多少
    [i]偷的话是[i-2]+v，不偷的话就是[i-1]，因为只用到了[i-2]和[i-1]所以没必要开数组
     */
    public static int rob1(int[] nums) {
//        int prepre = 0, pre = 0, max = 0;
//        for (int value : nums) {
//            max = Math.max(max, Math.max(prepre + value, pre));
//            prepre = pre;
//            pre = max;
//        }
//        return max;
        int len=nums.length;
        int[] f=new int[len+1];//f[i]表示第i个不偷的情况
        int[] g=new int[len+1];//g[i]表示第i个要偷的情况
        for(int i=1;i<=len;i++){
            int v=nums[i-1];
            f[i]=Math.max(f[i-1],g[i-1]);
            g[i]=v+f[i-1];
        }
        return Math.max(f[len],g[len]);
    }

    /*
    场景2
    数组是首尾相连的（计算两次rob1）
    如果考虑第一个房子，如果偷，最后一个去掉，如果不偷，和下面的情况重叠
    如果不考虑第一个房子，最后一个房子要考虑
     */
    public static int rob2(int[] nums) {
        int len=nums.length;
        if(len==1) return nums[0];
        int prepre=0,pre=0,max1=0,max2=0;
        for(int i=0;i<len-1;i++){
            int value=nums[i];
            max1=Math.max(max1,Math.max(pre,prepre+value));
            prepre=pre;
            pre=max1;
        }
        prepre=0;
        pre=0;
        for(int i=1;i<len;i++){
            int value=nums[i];
            max2=Math.max(max2,Math.max(pre,prepre+value));
            prepre=pre;
            pre=max2;
        }
        return Math.max(max1,max2);
    }

    /*
    场景3
    结构是一棵树，相邻两个节点被偷会报警，求最大值
    表示方法Map<TreeNode,Map<Integer,Integer>> dp
    dp.get(node).getOrDefault(0,0))和dp.get(node).getOrDefault(0,0))
    树状dp前者表示不使用i节点，i为根节点的最值，后者 表示使用i节点，i为根节点的最值
     */
    public static int rob3(TreeNode root){
        int[] ans=dfs(root);
        return Math.max(ans[0],ans[1]);
    }

    private static int[] dfs(TreeNode root) {
        if(root==null) return new int[2];
        int[] left=dfs(root.left);
        int[] right=dfs(root.right);
        int notRob=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        int rob=root.val+left[0]+right[0];
        return new int[]{notRob,rob};
    }


}
