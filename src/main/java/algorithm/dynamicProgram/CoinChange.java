package algorithm.dynamicProgram;

import java.util.Arrays;

public class CoinChange {

    /*
    硬币1：
    给定一堆已知面值的硬币，1,5,21,25，求能够凑出63的最少硬币数,如果不能凑出63就返回-1
    很明显不能用贪心，21*3才对

    硬币2：
    求凑出目标值的方案数
     */

    public static void main(String[] args) {
        int[] arr = new int[]{1,5,21,25};
        System.out.println(coinChange1(arr, 63));
        System.out.println(coinChange11(arr, 63));
        System.out.println(coinChange2(arr, 63));
        System.out.println(coinChange22(arr, 63));
    }

    /*
    硬币1 二维空间
     */
    static long coinChange1(int[] values, int target){
        //可以优化成滚动数组
        long[][] dp=new long[values.length+1][target+1];
        //不用硬币没法凑出1-target，设为无穷大
        Arrays.fill(dp[0],Integer.MAX_VALUE);
        dp[0][0]=0;
        for(int i=1;i<=values.length;i++){
            int value=values[i-1];
            for(int j=1;j<=target;j++){
                //不用当前硬币凑
                dp[i][j]=dp[i-1][j];
                if(value<=j)
                    dp[i][j]=Math.min(dp[i][j],dp[i][j-value]+1);
            }
        }
        return dp[values.length][target];
    }
    /*
    硬币1 一维空间
    因为只会用到[i-1][j]和[i][j-v],前者相当于是上一行同一列的状态，后者相当于是同一行前几列的状态
     */
    static int coinChange11(int[] values, int target) {
        long[] dp = new long[target + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int value:values){
            for(int t=1;t<=target;t++){
                if(value<=t)
                    dp[t]=Math.min(dp[t],dp[t-value]+1);
            }
        }
        int ans=(int)dp[target];
        return ans==Integer.MAX_VALUE?-1:ans;
    }



    /*
    硬币2 n*m空间
     */
    static int coinChange22(int[] coins,int amount){
        int[][] dp=new int[coins.length+1][amount+1];
        dp[0][0]=1;
        for(int i=1;i<=coins.length;i++){
            dp[i][0]=1;
            int coin=coins[i-1];
            for(int j=1;j<=amount;j++){
                //当前硬币不参数凑数
                dp[i][j]=dp[i-1][j];
                //当前硬币参与凑数，等价于当前硬币以及之前凑出j-coin
                if(coin<=j) dp[i][j]+=dp[i][j-coin];
            }
        }
        return dp[coins.length][amount];
    }

    /*
    硬币2 m空间
     */
    static int coinChange2(int[] coins,int amount){
        int[] dp=new int[amount+1];
        dp[0]=1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
