package algorithm.dynamicProgram;

public class NumberOfSum {
    /*
    求一个数组中和为sum的方案数，只要下标不一样就算，有点像完全背包
    暴力递归解法会超时O(2^n)
     */

    public static void main(String[] args) {
        int[] arr=new int[]{5,5,10,2,3};
        int target=15;
        System.out.println(helper(arr,target));
    }

    /*
    dp[i][j]表示前i个数字中和为j的方案数
    只要找出前i-1个内和为target和target-val的方案数相加就行
    转移方程dp[i][j]=dp[i-1][j]+dp[i-1][j-val]
     */
    private static int helper(int[] arr, int target) {
        int len=arr.length;
        int[][] dp=new int[len+1][target+1];//
        for(int i=0;i<=len;i++){
            dp[i][0]=1;//和为0只有一种，什么都不取
        }
        for(int i=1;i<=len;i++){
            for(int j=1;j<=target;j++){
                int val=arr[i-1];
                //前i个数字和为target的方案数一定大于等于前i-1个
                dp[i][j]=dp[i-1][j];
                //还要加上前i-1个和为target-cur的方案数,因为cur+(target-cur)==target
                if(j>=val) dp[i][j]+=dp[i-1][j-val];
            }
        }
        return dp[len][target];
    }

}
