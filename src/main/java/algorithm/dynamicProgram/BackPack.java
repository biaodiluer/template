package algorithm.dynamicProgram;

public class BackPack {

    /*
    01背包：
    有n种物品，每种物品只有一个，有最大重量为W的背包，能够装下最大价值的物品有多少

    完全背包：
    每种物品有无限个
     */

    public static void main(String[] args) {
        int[] weights = new int[]{1, 3, 2, 6, 2};
        int[] values = new int[]{2, 5, 3, 10, 4};
        int W = 12;
        System.out.println("01背包二维空间: "+pack01(W, weights, values));
        System.out.println("01背包一维空间: "+pack02(W, weights, values));
        System.out.println("完全背包n*w*times: "+packFull1(W, weights, values));
        System.out.println("完全背包二维空间: "+packFull2(W, weights, values));
        System.out.println("完全背包以为空间: "+packFull3(W, weights, values));
    }

    /*
    为什么可以优化成一个一维数组，因为在二维的时候是一行一行填的，并且只用到了上一行同一列和本行之前的位置，所以可以用一维数组
     */

    /*
    01背包二维空间做法
     */
    private static int pack01(int W, int[] weights, int[] values) {
        int nums = weights.length;
        //dp[i][j]:空闲重量为j时，前i个里面能拿的最大价值,可以用滚动数组降低空间复杂度加&就可以了
        int[][] dp = new int[nums+1][W + 1];
        for (int id = 1; id <= nums; id++) {
            int weight = weights[id - 1];
            int value = values[id - 1];
            for (int w = 1; w <= W; w++) {
                //不拿的情况
                dp[id][w] = dp[id - 1][w];
                if (weight <= w) {
                    dp[id][w] = Math.max(dp[id][w], dp[id - 1][w - weight] + value);
                }
            }
        }
        return dp[nums][W];
    }

    /*
    01背包一维空间做法
     */
    private static int pack02(int W, int[] weights, int[] values) {
        int nums = weights.length;
        int[] dp = new int[W + 1];
        for (int id = 0; id < nums; id++) {
            int weight = weights[id];
            int value = values[id];
            for(int j=W;j>=1;j--){
                if(weight<=j)
                    dp[j]=Math.max(dp[j],dp[j-weight]+value);
            }
        }
        return dp[W];
    }

    /*
    完全背包n*W空间，n*W*k时间
     */
    private static int packFull1(int W, int[] weights, int[] values) {
        int nums = weights.length;
        int[][] dp = new int[nums+1][W + 1];
        for (int id = 1; id <= nums; id++) {
            int weight = weights[id - 1];
            int value = values[id - 1];
            for (int w = 1; w <= W; w++) {
                for(int k=0;k*weight<=w;k++){
                    dp[id][w]=Math.max(dp[id][w],dp[id-1][w-k*weight]+k*value);
                }
            }
        }
        return dp[nums][W];
    }

    /*
    完全背包n*W空间，n*W时间
     */
    private static int packFull2(int W, int[] weights, int[] values) {
        int nums = weights.length;
        int[][] dp = new int[nums+1][W + 1];
        for (int id = 1; id <= nums; id++) {
            int weight = weights[id - 1];
            int value = values[id - 1];
            for (int w = 1; w <= W; w++) {
                dp[id][w]=dp[id-1][w];
                if(weight<=w)
                    dp[id][w]=Math.max(dp[id][w],dp[id][w-weight]+value);
            }
        }
        return dp[nums][W];
    }

    /*
    完全背包W空间，n*W时间，内循环从1开始
     */
    private static int packFull3(int W, int[] weights, int[] values) {
        int[] dp=new int[W+1];
        for(int i=0;i<weights.length;i++){
            int w=weights[i];
            int v=values[i];
            for(int j=1;j<=W;j++){
                if(w<=j)
                    dp[j]=Math.max(dp[j],dp[j-w]+v);
            }
        }
        return dp[W];
    }

}
