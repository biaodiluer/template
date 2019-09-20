package algorithm.dynamicProgram;

import java.util.Arrays;

public class BuyAndSellStock {

    /*
    给一个数组，每个元素表示当天股票的价格，求买进卖出的利润相关问题
     */

    public static void main(String[] args) {
        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit3(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit4(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit5(new int[]{7,1,5,3,6,4},2));
        System.out.println(maxProfit6(new int[]{1,2,3,0,2}));
    }

    /*
    最多只能买入卖出一次，求最大利益
    min保存之前的最小值，用当前价格减去min更新max
     */
    static public int maxProfit1(int[] prices) {
        if(prices==null || prices.length<2) return 0;
        int max=0;
        int min=prices[0];
        for(int price:prices){
            max=Math.max(max,price-min);
            min=Math.min(min,price);
        }
        return max;
    }

    /*
    不限买卖次数，下一次买之前必须要把现在的卖掉
    只要后一个比前一个大，就把差值累加
     */
    static public int maxProfit2(int[] prices) {
        if(prices==null || prices.length<2) return 0;
        int sum=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]) sum+=prices[i]-prices[i-1];
        }
        return sum;
    }

    /*
    最多买卖两次，左右各算一次，On空间
     */
    static public int maxProfit3(int[] prices){
        int len=prices.length;
        if(len<2) return 2;
        //从前往后算左边的,第i天之前的最大利润
        int[] left=new int[len];
        int min=prices[0];
        for(int i=1;i<len;i++){
            left[i]=Math.max(left[i-1],prices[i]-min);
            min=Math.min(min,prices[i]);
        }
        //从后往前算右边的，第i天之后的最大利润
        int[] right=new int[len];
        int max=prices[len-1];
        for(int i=len-2;i>=0;i--){
            right[i]=Math.max(right[i+1],max-prices[i]);
            max=Math.max(max,prices[i]);
        }
        int ans=0;
        for(int i=0;i<len;i++){
            ans=Math.max(ans,left[i]+right[i]);
        }
        return ans;
    }

    /*
    最多买卖两次，动态规划，O1空间
     */
    static public int maxProfit4(int[] prices){
        int len=prices.length;
        if(len<2) return 0;
        int buy1=Integer.MIN_VALUE,buy2=Integer.MIN_VALUE,sell1=0,sell2=0;
        for (int price : prices) {
            //第一次买进后的最大利益，全都是max是因为方便计算，并且统一
            buy1 = Math.max(buy1, -price);
            //第一次卖出后的最大利益
            sell1 = Math.max(sell1, buy1 + price);
            //第二次买进后的最大利益
            buy2 = Math.max(buy2, sell1 - price);
            //第二次卖出后的最大利益
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }


    /*
    最多买卖K次,没怎么看懂
     */
    static public int maxProfit5(int[] prices,int K){
        int len=prices.length;
        if(len<2) return 0;
        //如果K大于了长度，就相当于是不限次数了
        if(K>=len) return maxProfit2(prices);
        int[] hold=new int[K+1];//hold[k]表示最多进行了k次交易并且还持有股票
        int[] unHold=new int[K+1];//unHold[k]表示最多进行了k次交易并且没有持有股票
        Arrays.fill(hold,Integer.MIN_VALUE);
        for(int v:prices){
            for(int k=1;k<=K;k++){
                hold[k]=Math.max(hold[k],unHold[k-1]-v);
                unHold[k]=Math.max(unHold[k],hold[k]+v);
            }
        }
        return unHold[K];
    }

    /*
    不限次数买，但是卖出后必须要隔一天后才能再买入
    三个状态，买（包括卖之前的状态）|卖|冷却
     */
    static public int maxProfit6(int[] prices){
        int len=prices.length;
        if(len<2) return 0;
        int[][] dp=new int[len+1][3];//0|1|2分别代表买|卖|冷却
        dp[0][0]=Integer.MIN_VALUE;
        for(int i=1;i<=len;i++){
            int val=prices[i-1];
            //买的情况，max(前一天已经买入还未卖出的值，前一天为冷却的值)
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][2]-val);
            //卖的情况，只有前一天已经买入还未卖出的值+当前值
            dp[i][1]=dp[i-1][0]+val;
            //不动的情况，前一天就不动，前一天卖出
            dp[i][2]=Math.max(dp[i-1][2],dp[i-1][1]);
        }
        return Math.max(dp[len][1],dp[len][2]);
    }
}
